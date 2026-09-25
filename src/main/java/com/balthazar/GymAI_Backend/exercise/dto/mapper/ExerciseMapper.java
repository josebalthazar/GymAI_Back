package com.balthazar.GymAI_Backend.exercise.dto.mapper;

import com.balthazar.GymAI_Backend.exercise.dto.request.ExerciseRequest;
import com.balthazar.GymAI_Backend.exercise.dto.response.ExerciseResponse;
import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
import com.balthazar.GymAI_Backend.training.entity.Training;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    public ExerciseResponse toDTO (Exercise exercise) {
        if(exercise == null) {
            return null;
        }

        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getExpectedSeries(),
                exercise.getSeries()
        );
    }

    public Exercise toEntity (ExerciseRequest dto, Training training) {
        if(dto == null){
            return  null;
        }

        return new Exercise(
                dto.name(),
                dto.expectedSeries(),
                training
        );
    }
}
