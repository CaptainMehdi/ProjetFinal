package com.example.myappcore.service;

import com.example.myappcore.dto.BainsLibreDto;
import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.model.Cours;
import com.example.myappcore.repository.BainsLibreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BainsLibreService {

    private final BainsLibreRepository bainsLibreRepository;

    @Autowired
    public BainsLibreService(BainsLibreRepository bainsLibreRepository) {
        this.bainsLibreRepository = bainsLibreRepository;
    }

    public BainsLibreDto saveBainsLibre(BainsLibreDto bainsLibreDto) {
        if(bainsLibreDto != null){
            BainsLibre bainsLibre = new BainsLibre();
            bainsLibre.setId(bainsLibreDto.getId());
            bainsLibre.setBassin(bainsLibreDto.getBassin());
            bainsLibre.setSauveteurs(null);
            BainsLibre savedBainsLibre = bainsLibreRepository.save(bainsLibre);
            return new BainsLibreDto(savedBainsLibre);
        }
        return null;
    }

    public Optional<BainsLibreDto> getBainsLibreById(Long id) {
        Optional<BainsLibre> bainsLibreOptional = bainsLibreRepository.findById(id);
        return bainsLibreOptional.map(BainsLibreDto::new);
    }
}
