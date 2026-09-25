package com.balthazar.GymAI_Backend.auth.provider.factory;

import com.balthazar.GymAI_Backend.auth.enums.AuthProviderType;
import com.balthazar.GymAI_Backend.auth.provider.GoogleAuthProvider;
import com.balthazar.GymAI_Backend.auth.provider.LocalAuthProvider;
import com.balthazar.GymAI_Backend.auth.provider.contract.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProviderFactory {

    private final LocalAuthProvider localAuthProvider;
    private final GoogleAuthProvider googleAuthProvider;

    public AuthProvider getProvider(AuthProviderType provider) {

        return switch (provider) {
            case LOCAL -> localAuthProvider;
            case GOOGLE -> googleAuthProvider;
        };
    }
}