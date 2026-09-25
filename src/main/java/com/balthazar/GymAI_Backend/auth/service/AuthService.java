package com.balthazar.GymAI_Backend.auth.service;

import com.balthazar.GymAI_Backend.auth.enums.AuthProviderType;
import com.balthazar.GymAI_Backend.auth.provider.LocalAuthProvider;
import com.balthazar.GymAI_Backend.auth.provider.contract.AuthProvider;
import com.balthazar.GymAI_Backend.auth.provider.credentials.AuthCredentials;
import com.balthazar.GymAI_Backend.auth.provider.credentials.LocalCredentials;
import com.balthazar.GymAI_Backend.auth.provider.factory.AuthProviderFactory;
import com.balthazar.GymAI_Backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthProviderFactory providerFactory;
    private final LocalAuthProvider localAuthProvider;

    public User register(
            String name,
            String username,
            String email,
            String password,
            String avatar
    ) {

        return localAuthProvider.register(
                name,
                username,
                email,
                password,
                avatar
        );
    }

    public User login(
            AuthProviderType providerType,
            AuthCredentials credentials
    ) {

        AuthProvider provider =
                providerFactory.getProvider(providerType);

        return provider.authenticate(credentials);
    }
}