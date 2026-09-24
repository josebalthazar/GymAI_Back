package com.balthazar.GymAI_Backend.exercise.dto.request;

import com.balthazar.GymAI_Backend.serie.entity.Serie;

import java.util.List;

public record ExerciseRequest(
        String name,
        Integer countSeries
) {
}
