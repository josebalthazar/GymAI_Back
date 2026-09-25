package com.balthazar.GymAI_Backend.user.entity;

import com.balthazar.GymAI_Backend.statics.consistency.entity.ConsistencyStatistics;
import com.balthazar.GymAI_Backend.training.entity.Training;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String avatar;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Training> trainings = new ArrayList<>();
}
