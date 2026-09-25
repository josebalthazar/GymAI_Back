package com.balthazar.GymAI_Backend.statics.consistency.dto.response;

import java.time.LocalDate;

public record ConsistencyStatisticsResponse(
        Integer currentStreak,
        Integer longestStreak,
        Integer expectedWorkouts,
        Integer completedWorkouts,
        Double completionRate,
        LocalDate lastWorkoutDate
) {
}
