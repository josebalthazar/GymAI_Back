package com.balthazar.GymAI_Backend.auth.provider;

import com.balthazar.GymAI_Backend.auth.entity.UserAuthentication;
import com.balthazar.GymAI_Backend.auth.enums.AuthProviderType;
import com.balthazar.GymAI_Backend.auth.provider.contract.AuthProvider;
import com.balthazar.GymAI_Backend.auth.provider.credentials.AuthCredentials;
import com.balthazar.GymAI_Backend.auth.provider.credentials.GoogleCredentials;
import com.balthazar.GymAI_Backend.auth.repository.UserAuthenticationRepository;
import com.balthazar.GymAI_Backend.user.entity.User;
import com.balthazar.GymAI_Backend.user.repository.UserRepository;
import com.balthazar.GymAI_Backend.user.service.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class GoogleAuthProvider implements AuthProvider {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserAuthenticationRepository authenticationRepository;

    @Value("${google.client-id}")
    private String googleClientId;

    @Override
    public User authenticate(AuthCredentials credentials) {

        GoogleCredentials googleCredentials =
                (GoogleCredentials) credentials;

        GoogleIdToken.Payload payload = verifyToken(
                googleCredentials.idToken()
        );

        String googleUserId = payload.getSubject();
        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String avatar = (String) payload.get("picture");

        UserAuthentication authentication =
                authenticationRepository
                        .findByProviderAndProviderUserId(
                                AuthProviderType.GOOGLE,
                                googleUserId
                        )
                        .orElse(null);

        if (authentication != null) {
            return authentication.getUser();
        }

        User user = userRepository
                .findByEmail(email)
                .orElseGet(() ->
                        userService.create(
                                name,
                                generateUsername(name, googleUserId),
                                email,
                                avatar
                        )
                );

        UserAuthentication newAuthentication =
                new UserAuthentication(
                        user,
                        AuthProviderType.GOOGLE,
                        googleUserId,
                        null
                );

        authenticationRepository.save(newAuthentication);

        return user;
    }

    private GoogleIdToken.Payload verifyToken(String idToken) {

        try {

            GoogleIdTokenVerifier verifier =
                    new GoogleIdTokenVerifier.Builder(
                            new NetHttpTransport(),
                            GsonFactory.getDefaultInstance()
                    )
                            .setAudience(
                                    Collections.singletonList(googleClientId)
                            )
                            .build();

            GoogleIdToken googleIdToken =
                    verifier.verify(idToken);

            if (googleIdToken == null) {
                throw new RuntimeException("Invalid Google token.");
            }

            return googleIdToken.getPayload();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Could not validate Google token.",
                    e
            );
        }
    }

    private String generateUsername(
            String name,
            String googleUserId
    ) {
        String baseUsername = name
                .toLowerCase()
                .replaceAll("\\s+", "");

        return baseUsername + googleUserId.substring(0, 6);
    }
}