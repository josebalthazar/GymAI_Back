package com.balthazar.GymAI_Backend.auth.dto;


import com.balthazar.GymAI_Backend.auth.enums.AuthProviderType;

public record LoginRequest(
        AuthProviderType provider,
        String email,
        String password,
        String idToken
) {}
