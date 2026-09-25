package com.balthazar.GymAI_Backend.auth.dto;

import java.util.UUID;

public record AuthResponse(
        UUID userId,
        String username,
        String token
) {}
