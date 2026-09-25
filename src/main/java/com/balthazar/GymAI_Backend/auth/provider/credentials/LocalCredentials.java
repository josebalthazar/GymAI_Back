package com.balthazar.GymAI_Backend.auth.provider.credentials;

public record LocalCredentials(
        String email,
        String password
) implements AuthCredentials {
}