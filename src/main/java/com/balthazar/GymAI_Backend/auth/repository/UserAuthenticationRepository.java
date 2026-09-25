package com.balthazar.GymAI_Backend.auth.repository;

import com.balthazar.GymAI_Backend.auth.entity.UserAuthentication;
import com.balthazar.GymAI_Backend.auth.enums.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long > {

    Optional<UserAuthentication> findByProviderAndProviderUserId(
            AuthProviderType provider,
            String providerUserId
    );

    Optional<UserAuthentication> findByUserIdAndProvider(
            UUID userId,
            AuthProviderType provider
    );
}
