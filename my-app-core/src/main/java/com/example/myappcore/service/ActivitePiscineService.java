package com.example.myappcore.service;

import com.example.myappcore.dto.ActivitePiscineDto;
import com.example.myappcore.model.ActivitePiscine;
import com.example.myappcore.repository.ActivitePiscineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivitePiscineService {

    private ActivitePiscineRepository activitePiscineRepository;

    public ActivitePiscineService(ActivitePiscineRepository activitePiscineRepository) {
        this.activitePiscineRepository = activitePiscineRepository;
    }

    public List<ActivitePiscineDto> getAllActivities() {
        List<ActivitePiscine> activities = activitePiscineRepository.findAll();

        List<ActivitePiscineDto> activityDtos = new ArrayList<>();
        for (ActivitePiscine activity : activities) {
            ActivitePiscineDto dto = convertToDto(activity);
            activityDtos.add(dto);
        }
        return activityDtos;
    }

    private ActivitePiscineDto convertToDto(ActivitePiscine activitePiscine) {
        ActivitePiscineDto dto = new ActivitePiscineDto();
        dto.setId(activitePiscine.getId());
        dto.setBassin(activitePiscine.getBassin());
        return dto;
    }
}
