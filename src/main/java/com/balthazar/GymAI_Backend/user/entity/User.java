package com.balthazar.GymAI_Backend.user.entity;

import com.balthazar.GymAI_Backend.statics.entity.Statics;
import com.balthazar.GymAI_Backend.training.entity.Training;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String avatar;

    @OneToOne
    private Statics statics;

    @ManyToOne
    private List<Training> trainings;
}
