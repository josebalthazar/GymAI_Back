package com.balthazar.GymAI_Backend.auth.provider;

import com.balthazar.GymAI_Backend.auth.entity.UserAuthentication;
import com.balthazar.GymAI_Backend.auth.enums.AuthProviderType;
import com.balthazar.GymAI_Backend.auth.provider.contract.AuthProvider;
import com.balthazar.GymAI_Backend.auth.provider.credentials.AuthCredentials;
import com.balthazar.GymAI_Backend.auth.provider.credentials.LocalCredentials;
import com.balthazar.GymAI_Backend.auth.repository.UserAuthenticationRepository;
import com.balthazar.GymAI_Backend.user.entity.User;
import com.balthazar.GymAI_Backend.user.repository.UserRepository;
import com.balthazar.GymAI_Backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalAuthProvider implements AuthProvider {

    private final UserRepository userRepository;
    private final UserAuthenticationRepository authenticationRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public User register(
            String name,
            String username,
            String email,
            String password,
            String avatar
    ) {

        User user = userService.create(
                name,
                username,
                email,
                avatar
        );

        UserAuthentication authentication =
                new UserAuthentication(
                        user,
                        AuthProviderType.LOCAL,
                        user.getId().toString(),
                        passwordEncoder.encode(password)
                );

        authenticationRepository.save(authentication);

        return user;
    }

    @Override
    public User authenticate(AuthCredentials credentials) {

        LocalCredentials localCredentials =
                (LocalCredentials) credentials;

        User user = userRepository
                .findByEmail(localCredentials.email())
                .orElseThrow(() ->
                        new RuntimeException("Invalid credentials.")
                );

        UserAuthentication authentication =
                authenticationRepository
                        .findByUserIdAndProvider(
                                user.getId(),
                                AuthProviderType.LOCAL
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Authentication not found."
                                )
                        );

        if (!passwordEncoder.matches(
                localCredentials.password(),
                authentication.getPasswordHash()
        )) {
            throw new RuntimeException("Invalid credentials.");
        }

        return user;
    }
}