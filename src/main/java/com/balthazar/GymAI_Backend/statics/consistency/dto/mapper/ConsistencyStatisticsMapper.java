package com.balthazar.GymAI_Backend.statics.consistency.dto.mapper;

import com.balthazar.GymAI_Backend.statics.consistency.dto.response.ConsistencyStatisticsResponse;
import com.balthazar.GymAI_Backend.statics.consistency.entity.ConsistencyStatistics;

public class ConsistencyStatisticsMapper {
    public ConsistencyStatisticsResponse toDto(ConsistencyStatistics statistics) {
        return new ConsistencyStatisticsResponse(
                statistics.getCurrentStreak(),
                statistics.getLongestStreak(),
                statistics.getExpectedWorkouts(),
                statistics.getCompletedWorkouts(),
                statistics.getCompletionRate(),
                statistics.getLastWorkoutDate()
        );
    }
}
