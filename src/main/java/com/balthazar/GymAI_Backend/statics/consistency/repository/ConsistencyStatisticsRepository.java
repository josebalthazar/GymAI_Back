package com.balthazar.GymAI_Backend.statics.consistency.repository;

import com.balthazar.GymAI_Backend.statics.consistency.entity.ConsistencyStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConsistencyStatisticsRepository extends JpaRepository<ConsistencyStatistics, Long> {
    Optional<ConsistencyStatistics> findByUserId(UUID userId);
}
