package com.balthazar.GymAI_Backend.serie.dto.mapper;

import com.balthazar.GymAI_Backend.serie.dto.request.SerieRequest;
import com.balthazar.GymAI_Backend.serie.dto.response.SerieResponse;
import com.balthazar.GymAI_Backend.serie.entity.Serie;
import org.springframework.stereotype.Component;

@Component
public class SerieMapper {

    public SerieResponse toDto(Serie serie){
        if(serie == null){
            return null;
        }
        return new SerieResponse(
                serie.getId(),
                serie.getWeight(),
                serie.getReps(),
                serie.getDifficulty(),
                serie.getCreatedAt()
        );
    }

    public Serie toEntity(SerieRequest dto) {
        if(dto == null){
            return null;
        }
        return new Serie(
                dto.weight(),
                dto.reps(),
                dto.difficulty()
        );
    }
}
