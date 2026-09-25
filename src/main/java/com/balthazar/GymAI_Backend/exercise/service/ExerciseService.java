package com.balthazar.GymAI_Backend.exercise.service;

import com.balthazar.GymAI_Backend.exercise.dto.mapper.ExerciseMapper;
import com.balthazar.GymAI_Backend.exercise.dto.request.ExerciseRequest;
import com.balthazar.GymAI_Backend.exercise.dto.response.ExerciseResponse;
import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
import com.balthazar.GymAI_Backend.exercise.repository.ExerciseRepository;
import com.balthazar.GymAI_Backend.training.entity.Training;
import com.balthazar.GymAI_Backend.training.repository.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;
    private final ExerciseMapper mapper;

    public ExerciseResponse create(Long trainingId, ExerciseRequest request) {

        Training training = trainingRepository.findById(trainingId)
                .orElseThrow(() ->
                        new RuntimeException("The training does not exist.")
                );
        if (exerciseRepository.existsByNameAndTrainingId(
                request.name(),
                trainingId
        )) {
            throw new RuntimeException(
                    "Exercise already exists in this training."
            );
        }
        Exercise newExercise = mapper.toEntity(request, training);
        Exercise savedExercise = exerciseRepository.save(newExercise);

        return mapper.toDTO(savedExercise);
    }

    public ExerciseResponse getById(UUID id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The exercise does not exist.")
                );
        return mapper.toDTO(exercise);
    }

    public ExerciseResponse patch(UUID id, ExerciseRequest request) {

        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The exercise does not exist.")
                );
        Long trainingId = exercise.getTraining().getId();
        if (exerciseRepository.existsByNameAndTrainingIdAndIdNot(
                request.name(),
                trainingId,
                id
        )) {
            throw new RuntimeException(
                    "Exercise already exists in this training."
            );
        }
        exercise.alterar(
                request.name(),
                request.countSeries()
        );
        Exercise updatedExercise = exerciseRepository.save(exercise);

        return mapper.toDTO(updatedExercise);
    }

    public void delete(UUID id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("The exercise does not exist.")
                );
        exerciseRepository.delete(exercise);
    }
}
