package com.balthazar.GymAI_Backend.serie.dto.response;

import com.balthazar.GymAI_Backend.serie.enums.DifficultyEnum;

import java.time.LocalDate;

public record SerieResponse(
        Integer id,
        Double weight,
        Integer reps,
        DifficultyEnum difficulty,
        LocalDate createdAt
) {
}
