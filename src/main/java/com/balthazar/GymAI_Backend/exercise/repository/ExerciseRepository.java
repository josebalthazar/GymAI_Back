package com.balthazar.GymAI_Backend.exercise.repository;

import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {

    boolean existsByName(String name);
}
