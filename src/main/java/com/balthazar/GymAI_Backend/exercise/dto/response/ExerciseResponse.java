package com.balthazar.GymAI_Backend.exercise.dto.response;

import com.balthazar.GymAI_Backend.serie.entity.Serie;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ExerciseResponse(
        UUID id,
        String name,
        Integer expectedSeries,
        List<Serie> series
) {
}
