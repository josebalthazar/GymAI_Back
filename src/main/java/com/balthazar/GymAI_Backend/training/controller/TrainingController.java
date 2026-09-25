package com.balthazar.GymAI_Backend.training.controller;

import com.balthazar.GymAI_Backend.training.dto.request.TrainingRequest;
import com.balthazar.GymAI_Backend.training.dto.response.TrainingResponse;
import com.balthazar.GymAI_Backend.training.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainings")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService service;

    @PostMapping
    public ResponseEntity<TrainingResponse> create(
            @RequestBody TrainingRequest request
    ) {

        TrainingResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingResponse> getById(
            @PathVariable Long id
    ) {

        TrainingResponse response = service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrainingResponse> patch(
            @PathVariable Long id,
            @RequestBody TrainingRequest request
    ) {

        TrainingResponse response = service.patch(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}