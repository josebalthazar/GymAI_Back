package com.balthazar.GymAI_Backend.user.service;

import com.balthazar.GymAI_Backend.user.entity.User;
import com.balthazar.GymAI_Backend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User create(
            String name,
            String username,
            String email,
            String avatar
    ) {

        if (repository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists.");
        }

        if (repository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists.");
        }

        User user = new User(
                name,
                username,
                email,
                avatar
        );

        return repository.save(user);
    }
}
