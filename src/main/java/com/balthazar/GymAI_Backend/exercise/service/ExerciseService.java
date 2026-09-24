package com.balthazar.GymAI_Backend.exercise.service;

import com.balthazar.GymAI_Backend.exercise.dto.mapper.ExerciseMapper;
import com.balthazar.GymAI_Backend.exercise.dto.request.ExerciseRequest;
import com.balthazar.GymAI_Backend.exercise.dto.response.ExerciseResponse;
import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
import com.balthazar.GymAI_Backend.exercise.repository.ExerciseRepository;
import com.balthazar.GymAI_Backend.training.repository.TrainingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;
    private final ExerciseMapper mapper;


    public ExerciseResponse create(Long trainingId, ExerciseRequest request) {
        if(!trainingRepository.existsById(trainingId)){
            throw new RuntimeException("The training does not exist.");
        }
        if(exerciseRepository.existsByName(request.name())){
            throw new RuntimeException("Exercise name already exists.");
        }
        Exercise newExercise = mapper.toEntity(request);
        return mapper.toDTO(newExercise);
    }



}
