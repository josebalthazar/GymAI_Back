package com.balthazar.GymAI_Backend.training.dto.mapper;

import com.balthazar.GymAI_Backend.training.dto.request.TrainingRequest;
import com.balthazar.GymAI_Backend.training.dto.response.TrainingResponse;
import com.balthazar.GymAI_Backend.training.entity.Training;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public TrainingResponse toDTO(Training training){
        if (training == null) {
            return  null;
        }
        return new TrainingResponse(
                training.getId(),
                training.getName(),
                training.getExercises(),
                training.getDayOfWeeks(),
                training.getCreatedAt()
        );
    }

    public Training toEntity(TrainingRequest dto){
        if(dto == null) {
            return null;
        }

        return new Training(
                dto.name(),
                dto.dayOfWeeks()
        );
    }
}
