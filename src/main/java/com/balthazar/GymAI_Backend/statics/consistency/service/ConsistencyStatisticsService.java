package com.balthazar.GymAI_Backend.statics.consistency.service;

import com.balthazar.GymAI_Backend.statics.consistency.dto.mapper.ConsistencyStatisticsMapper;
import com.balthazar.GymAI_Backend.statics.consistency.dto.response.ConsistencyStatisticsResponse;
import com.balthazar.GymAI_Backend.statics.consistency.entity.ConsistencyStatistics;
import com.balthazar.GymAI_Backend.statics.consistency.repository.ConsistencyStatisticsRepository;
import com.balthazar.GymAI_Backend.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ConsistencyStatisticsService {

    private final ConsistencyStatisticsRepository repository;
    private final ConsistencyStatisticsMapper mapper;

    public ConsistencyStatistics create(User user) {

        ConsistencyStatistics statistics =
                ConsistencyStatistics.create(user);

        return repository.save(statistics);
    }

    public ConsistencyStatisticsResponse getByUser(UUID userId) {
        ConsistencyStatistics statistics = repository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Consistency statistics not found.")
                );

        return mapper.toDto(statistics);
    }
}