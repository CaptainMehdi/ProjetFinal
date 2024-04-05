package com.example.myappcore.service;

import com.example.myappcore.dto.ActivitePiscineDto;
import com.example.myappcore.dto.CoursAjoutDto;
import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.model.ActivitePiscine;
import com.example.myappcore.model.Cours;
import com.example.myappcore.repository.CoursRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursService {
    private CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public List<CoursDto> getAllCours() {
        List<Cours> coursList = coursRepository.findAll();

        List<CoursDto> coursDtos = new ArrayList<>();
        for (Cours cours : coursList) {
            CoursDto dto = convertToDto(cours);
            coursDtos.add(dto);
        }
        return coursDtos;
    }

    private CoursDto convertToDto(Cours activitePiscine) {
        CoursDto dto = new CoursDto();

        return dto;
    }

    public Cours saveCours(Cours cours){
        return coursRepository.save(cours);
    }
}
