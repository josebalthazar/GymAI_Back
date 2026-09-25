package com.balthazar.GymAI_Backend.serie.entity;

import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
import com.balthazar.GymAI_Backend.serie.enums.DifficultyEnum;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "series")
@Getter
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Double weight;

    private Integer reps;

    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private LocalDate createdAt;

    public Serie(
            Double weight,
            Integer reps,
            DifficultyEnum difficulty,
            Exercise exercise
    ) {
        this.weight = weight;
        this.reps = reps;
        this.difficulty = difficulty;
        this.exercise = exercise;
    }

    protected Serie() {
    }

    public void alterar(
            Double weight,
            Integer reps,
            DifficultyEnum difficulty
    ) {
        this.weight = weight;
        this.reps = reps;
        this.difficulty = difficulty;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}