package com.example.myappcore.service;

import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.model.Cours;
import com.example.myappcore.repository.BainsLibreRepository;
import org.springframework.stereotype.Service;

@Service
public class BainsLibreService {

    private BainsLibreRepository bainsLibreRepository;


    public BainsLibreService(BainsLibreRepository bainsLibreRepository) {
        this.bainsLibreRepository = bainsLibreRepository;
    }

    public BainsLibre saveBainsLibre(BainsLibre bainsLibre){
        return bainsLibreRepository.save(bainsLibre);
    }
}
