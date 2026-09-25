package com.balthazar.GymAI_Backend.exercise.entity;

import com.balthazar.GymAI_Backend.serie.entity.Serie;
import com.balthazar.GymAI_Backend.training.entity.Training;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exercise")
@Getter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Integer expectedSeries;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @OneToMany(
            mappedBy = "exercise",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Serie> series = new ArrayList<>();

    private LocalDate createdAt;

    public Exercise(String name, Integer expectedSeries, Training training) {
        this.name = name;
        this.expectedSeries = expectedSeries;
        this.training = training;
    }

    public void alterar(String name, Integer expectedSeries) {
        this.name = name;
        this.expectedSeries = expectedSeries;
    }

    protected Exercise() {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }
}
