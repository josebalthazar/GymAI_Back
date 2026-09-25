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

    private DifficultyEnum difficulty;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private LocalDate createdAt;

    public Serie(Double weight, Integer reps, DifficultyEnum difficulty) {
        this.weight = weight;
        this.reps = reps;
        this.difficulty = difficulty;
    }

    public void alterar(Double weight, Integer reps, DifficultyEnum difficulty) {
        this.weight = weight;
        this.reps = reps;
        this.difficulty = difficulty;
    }
}
