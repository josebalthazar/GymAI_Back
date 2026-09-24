package com.balthazar.GymAI_Backend.training.dto.request;

import java.time.DayOfWeek;
import java.util.List;

public record TrainingRequest(
        String name,
        List<DayOfWeek> dayOfWeeks
) {
}
