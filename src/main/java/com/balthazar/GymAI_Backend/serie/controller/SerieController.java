package com.balthazar.GymAI_Backend.serie.controller;

import com.balthazar.GymAI_Backend.serie.dto.request.SerieRequest;
import com.balthazar.GymAI_Backend.serie.dto.response.SerieResponse;
import com.balthazar.GymAI_Backend.serie.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class SerieController {

    private final SerieService service;

    @PostMapping("/exercise/{exerciseId}")
    public ResponseEntity<SerieResponse> create(
            @PathVariable UUID exerciseId,
            @RequestBody SerieRequest request
    ) {

        SerieResponse response =
                service.create(exerciseId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SerieResponse> getById(
            @PathVariable Integer id
    ) {

        SerieResponse response =
                service.getById(id);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SerieResponse> patch(
            @PathVariable Integer id,
            @RequestBody SerieRequest request
    ) {

        SerieResponse response =
                service.patch(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
