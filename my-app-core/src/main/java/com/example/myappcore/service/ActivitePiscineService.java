package com.example.myappcore.service;

import com.example.myappcore.dto.ActivitePiscineDto;
import com.example.myappcore.dto.BainsLibreDto;
import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.model.ActivitePiscine;
import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.model.Cours;
import com.example.myappcore.repository.ActivitePiscineRepository;
import com.example.myappcore.repository.BainsLibreRepository;
import com.example.myappcore.repository.CoursRepository;
import com.example.myappcore.utils.Bassin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivitePiscineService {

    private ActivitePiscineRepository activitePiscineRepository;
    private BainsLibreRepository bainsLibreRepository;
    private CoursRepository coursRepository;

    public ActivitePiscineService(ActivitePiscineRepository activitePiscineRepository, BainsLibreRepository bainsLibreRepository, CoursRepository coursRepository) {
        this.activitePiscineRepository = activitePiscineRepository;
        this.bainsLibreRepository = bainsLibreRepository;
        this.coursRepository = coursRepository;
    }

    public List<ActivitePiscineDto> getAllActivities() {
        List<Cours> coursList = coursRepository.findAll();
        List<BainsLibre> bainsLibreList = bainsLibreRepository.findAll();

        List<ActivitePiscineDto> allActivities = coursList.stream()
                .map(CoursDto::new)
                .collect(Collectors.toList());

        allActivities.addAll(bainsLibreList.stream()
                .map(BainsLibreDto::new)
                .collect(Collectors.toList()));

        return allActivities;
    }

    public ActivitePiscine getActivitiesById(Long id){
        return activitePiscineRepository.getById(id);
    }
}
