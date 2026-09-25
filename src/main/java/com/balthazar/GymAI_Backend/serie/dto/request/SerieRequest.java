package com.balthazar.GymAI_Backend.serie.dto.request;

import com.balthazar.GymAI_Backend.serie.enums.DifficultyEnum;

public record SerieRequest(
        Double weight,
        Integer reps,
        DifficultyEnum difficulty
) {
}
