package com.balthazar.GymAI_Backend.training.service;

import com.balthazar.GymAI_Backend.training.dto.mapper.TrainingMapper;
import com.balthazar.GymAI_Backend.training.dto.request.TrainingRequest;
import com.balthazar.GymAI_Backend.training.dto.response.TrainingResponse;
import com.balthazar.GymAI_Backend.training.entity.Training;
import com.balthazar.GymAI_Backend.training.repository.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainingService {

    private final TrainingRepository repository;
    private final TrainingMapper mapper;

    public TrainingResponse create(TrainingRequest request) {
        if (repository.existsByName(request.name())) {
            throw new RuntimeException("Workout name already exists.");
        }
        Training newTraining = mapper.toEntity(request);
        Training savedTraining = repository.save(newTraining);

        return mapper.toDTO(savedTraining);
    }

    public TrainingResponse getById(Long id) {
        Training training = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The training does not exist.")
                );
        return mapper.toDTO(training);
    }

    public TrainingResponse patch(Long id, TrainingRequest request) {

        Training training = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The training does not exist.")
                );
        if (repository.existsByNameAndIdNot(request.name(), id)) {
            throw new RuntimeException("Workout name already exists.");
        }
        training.alterar(
                request.name(),
                request.dayOfWeeks()
        );
        Training updated = repository.save(training);

        return mapper.toDTO(updated);
    }

    public void delete(Long id) {
        Training training = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The training does not exist.")
                );
        repository.delete(training);
    }
}
