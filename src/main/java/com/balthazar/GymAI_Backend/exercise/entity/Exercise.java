package com.balthazar.GymAI_Backend.exercise.entity;

import com.balthazar.GymAI_Backend.serie.entity.Serie;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exercises")
@Getter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Integer countSeries;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<Serie> series = new ArrayList<>();

    private LocalDate updatedAt;

    public Exercise(String name, Integer countSeries) {
        this.name = name;
        this.countSeries = countSeries;
    }
}
