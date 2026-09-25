package com.balthazar.GymAI_Backend.statics.consistency.controller;

import com.balthazar.GymAI_Backend.statics.consistency.dto.response.ConsistencyStatisticsResponse;
import com.balthazar.GymAI_Backend.statics.consistency.service.ConsistencyStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/statistics/consistency")
@RequiredArgsConstructor
public class ConsistencyStatisticsController {

    private final ConsistencyStatisticsService service;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ConsistencyStatisticsResponse> getByUser(
            @PathVariable UUID userId
    ) {

        ConsistencyStatisticsResponse response =
                service.getByUser(userId);

        return ResponseEntity.ok(response);
    }
}