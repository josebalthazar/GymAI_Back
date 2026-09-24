package com.balthazar.GymAI_Backend.statics.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "statics")
public class Statics {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
