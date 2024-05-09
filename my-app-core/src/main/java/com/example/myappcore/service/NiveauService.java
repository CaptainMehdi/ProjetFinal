package com.example.myappcore.service;

import com.example.myappcore.dto.NiveauDto;
import com.example.myappcore.model.Niveau;
import com.example.myappcore.repository.NiveauRepository;
import com.example.myappcore.utils.Bassin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NiveauService {
    private NiveauRepository niveauRepository;

    public NiveauService(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    public NiveauDto saveNiveau(NiveauDto niveauDto){
        niveauRepository.save(niveauDto.toEntity());
        return niveauDto;
    }

    public List<NiveauDto> getAllNiveaux(Bassin bassin) {
        Optional<List<Niveau>> niveaux = niveauRepository.getNiveauByBassin(bassin);
        List<NiveauDto> niveauDtos = new ArrayList<>();

        if(niveaux.isPresent()){
            for(Niveau niveau : niveaux.get()){
                niveauDtos.add(new NiveauDto(niveau));
            }
        }
        return niveauDtos;
    }
}
