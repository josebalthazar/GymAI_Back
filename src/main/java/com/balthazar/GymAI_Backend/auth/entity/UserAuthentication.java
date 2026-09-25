package com.balthazar.GymAI_Backend.auth.entity;

import com.balthazar.GymAI_Backend.auth.enums.AuthProviderType;
import com.balthazar.GymAI_Backend.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Table(
        name = "user_authentication",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_provider_user",
                        columnNames = {"provider", "provider_user_id"}
                )
        }
)
@Getter
public class UserAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProviderType provider;

    @Column(name = "provider_user_id", nullable = false)
    private String providerUserId;

    private String passwordHash;

    protected UserAuthentication() {
    }

    public UserAuthentication(
            User user,
            AuthProviderType provider,
            String providerUserId,
            String passwordHash
    ) {
        this.user = user;
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.passwordHash = passwordHash;
    }
}