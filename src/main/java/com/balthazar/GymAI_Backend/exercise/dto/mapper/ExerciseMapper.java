package com.balthazar.GymAI_Backend.exercise.dto.mapper;

import com.balthazar.GymAI_Backend.exercise.dto.request.ExerciseRequest;
import com.balthazar.GymAI_Backend.exercise.dto.response.ExerciseResponse;
import com.balthazar.GymAI_Backend.exercise.entity.Exercise;
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
                exercise.getCountSeries(),
                exercise.getSeries(),
                exercise.getUpdatedAt()
        );
    }

    public Exercise toEntity (ExerciseRequest dto) {
        if(dto == null){
            return  null;
        }

        return new Exercise(
                dto.name(),
                dto.countSeries()
        );
    }
}
