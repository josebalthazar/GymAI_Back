package com.balthazar.GymAI_Backend.training.entity;

import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training")
@Getter
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "training_exercise",
            joinColumns = @JoinColumn(name = "training_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises = new ArrayList<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> dayOfWeeks = new ArrayList<>();

    private LocalDate createdAt;

    public Training(String name, List<DayOfWeek> dayOfWeeks) {
        this.name = name;
        this.dayOfWeeks = dayOfWeeks;
    }

    public void alterar(String name, List<DayOfWeek> dayOfWeeks) {
        this.name = name;
        this.dayOfWeeks = dayOfWeeks;
    }
}
