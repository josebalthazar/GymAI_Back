package com.balthazar.GymAI_Backend.serie.service;

import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
import com.balthazar.GymAI_Backend.exercise.repository.ExerciseRepository;
import com.balthazar.GymAI_Backend.serie.dto.mapper.SerieMapper;
import com.balthazar.GymAI_Backend.serie.dto.request.SerieRequest;
import com.balthazar.GymAI_Backend.serie.dto.response.SerieResponse;
import com.balthazar.GymAI_Backend.serie.entity.Serie;
import com.balthazar.GymAI_Backend.serie.repository.SerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SerieService {

    private final SerieRepository repository;
    private final ExerciseRepository exerciseRepository;
    private final SerieMapper mapper;

    public SerieResponse create(
            UUID exerciseId,
            SerieRequest request
    ) {

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() ->
                        new RuntimeException("Exercise does not exist.")
                );

        Serie newSerie = mapper.toEntity(request, exercise);

        Serie savedSerie = repository.save(newSerie);

        return mapper.toDto(savedSerie);
    }

    public SerieResponse getById(Integer id) {

        Serie serie = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Serie does not exist.")
                );

        return mapper.toDto(serie);
    }

    public SerieResponse patch(
            Integer id,
            SerieRequest request
    ) {

        Serie serie = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Serie does not exist.")
                );

        serie.alterar(
                request.weight(),
                request.reps(),
                request.difficulty()
        );

        Serie updated = repository.save(serie);

        return mapper.toDto(updated);
    }

    public void delete(Integer id) {

        Serie serie = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Serie does not exist.")
                );

        repository.delete(serie);
    }
}
