package com.balthazar.GymAI_Backend.auth.controller;

import com.balthazar.GymAI_Backend.auth.dto.AuthResponse;
import com.balthazar.GymAI_Backend.auth.dto.LoginRequest;
import com.balthazar.GymAI_Backend.auth.dto.RegisterRequest;
import com.balthazar.GymAI_Backend.auth.provider.credentials.AuthCredentials;
import com.balthazar.GymAI_Backend.auth.provider.credentials.GoogleCredentials;
import com.balthazar.GymAI_Backend.auth.provider.credentials.LocalCredentials;
import com.balthazar.GymAI_Backend.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody RegisterRequest request
    ) {

        authService.register(
                request.name(),
                request.username(),
                request.email(),
                request.password(),
                request.avatar()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {

        AuthCredentials credentials = createCredentials(request);

        AuthResponse response = authService.login(
                request.provider(),
                credentials
        );

        return ResponseEntity.ok(response);
    }

    private AuthCredentials createCredentials(LoginRequest request) {

        return switch (request.provider()) {

            case LOCAL -> new LocalCredentials(
                    request.email(),
                    request.password()
            );

            case GOOGLE -> new GoogleCredentials(
                    request.idToken()
            );
        };
    }
}