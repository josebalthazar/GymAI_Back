package com.balthazar.GymAI_Backend.training.dto.response;

import com.balthazar.GymAI_Backend.exercise.entity.Exercise;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public record TrainingResponse (
        Long id,
        String name,
        List<Exercise> exercises,
        List<DayOfWeek> dayOfWeeks,
        LocalDate createdAt
) {
}