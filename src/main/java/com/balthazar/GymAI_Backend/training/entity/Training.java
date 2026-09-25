package com.balthazar.GymAI_Backend.training.entity;

import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
import com.balthazar.GymAI_Backend.user.entity.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    @OneToMany(
            mappedBy = "training",
            cascade = CascadeType.ALL,
            orphanRemoval = true
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

    protected Training() {
    }

    public void alterar(String name, List<DayOfWeek> dayOfWeeks) {
        this.name = name;
        this.dayOfWeeks = dayOfWeeks;
    }

    @PrePersist
    protected  void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
