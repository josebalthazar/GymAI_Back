package com.balthazar.GymAI_Backend.serie.service;

import com.balthazar.GymAI_Backend.serie.dto.mapper.SerieMapper;
import com.balthazar.GymAI_Backend.serie.dto.request.SerieRequest;
import com.balthazar.GymAI_Backend.serie.dto.response.SerieResponse;
import com.balthazar.GymAI_Backend.serie.entity.Serie;
import com.balthazar.GymAI_Backend.serie.repository.SerieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SerieService {

    private final SerieRepository repository;
    private final SerieMapper mapper;

    public SerieResponse create (SerieRequest request) {
        Serie newSerie = mapper.toEntity(request);
        return mapper.toDto(newSerie);
    }

    public SerieResponse patch (Integer id, SerieRequest request) {
        Serie serie = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serie does not exist."));
        serie.alterar(
                request.weight(),
                request.reps(),
                request.difficulty()
        );
        Serie updated = repository.save(serie);
        return mapper.toDto(updated);
    }

    public void delete (Integer id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("Serie does not exist.");
        }
    }



}
