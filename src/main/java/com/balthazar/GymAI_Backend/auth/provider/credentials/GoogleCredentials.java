package com.balthazar.GymAI_Backend.auth.provider.credentials;

public record GoogleCredentials(
        String idToken
) implements AuthCredentials {
}