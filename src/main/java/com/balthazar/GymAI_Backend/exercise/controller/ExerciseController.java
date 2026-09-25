package com.balthazar.GymAI_Backend.exercise.controller;

import com.balthazar.GymAI_Backend.exercise.dto.request.ExerciseRequest;
import com.balthazar.GymAI_Backend.exercise.dto.response.ExerciseResponse;
import com.balthazar.GymAI_Backend.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService service;

    @PostMapping("/training/{trainingId}")
    public ResponseEntity<ExerciseResponse> create(
            @PathVariable Long trainingId,
            @RequestBody ExerciseRequest request
    ) {

        ExerciseResponse response =
                service.create(trainingId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponse> getById(
            @PathVariable UUID id
    ) {

        ExerciseResponse response =
                service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExerciseResponse> patch(
            @PathVariable UUID id,
            @RequestBody ExerciseRequest request
    ) {

        ExerciseResponse response =
                service.patch(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
