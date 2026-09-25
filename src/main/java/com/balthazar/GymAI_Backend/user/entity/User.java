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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String avatar;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Training> trainings = new ArrayList<>();

    protected User() {
    }

    public User(String name, String username, String email, String avatar) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
    }

    public void alterarNome(String name) {
        this.name = name;
    }

    public void alterarAvatar(String avatar) {
        this.avatar = avatar;
    }
}