package com.example.myappcore.service;

import com.example.myappcore.dto.NiveauDto;
import com.example.myappcore.model.Niveau;
import com.example.myappcore.repository.NiveauRepository;
import org.springframework.stereotype.Service;

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
}
