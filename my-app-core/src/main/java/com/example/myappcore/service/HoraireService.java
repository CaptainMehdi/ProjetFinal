package com.example.myappcore.service;

import com.example.myappcore.dto.HoraireDto;
import com.example.myappcore.model.Horaire;
import com.example.myappcore.repository.HoraireRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HoraireService {
    private HoraireRepository horaireRepository;
    private ActivitePiscineService activitePiscineService;

    public HoraireService(HoraireRepository horaireRepository, ActivitePiscineService activitePiscineService) {
        this.horaireRepository = horaireRepository;
        this.activitePiscineService = activitePiscineService;
    }

    public HoraireDto save(HoraireDto horaireDto){
        if(horaireDto != null){
            Horaire horaire = new Horaire();
            horaire.setTo(horaireDto.getTo());
            horaire.setFrom(horaireDto.getFrom());
            horaire.setName(horaireDto.getName());
            horaire.setActivitePiscine(activitePiscineService.getActivitiesById(horaireDto.getActivitePiscineId()));
            horaire.setBassin(horaireDto.getBassin());
            horaire.setLongueur(horaireDto.getLongueur());
            horaire.setDay(horaireDto.getDay());
            horaireRepository.save(horaire);
            return horaireDto;
        }
        return null;
    }

    public List<HoraireDto> getAllHoraire(){
        Optional<List<Horaire>> horairesOptional = horaireRepository.getAllHoraire();
        List<HoraireDto> horaireDtos = new ArrayList<>();

        if (horairesOptional.isPresent()) {
            for (Horaire horaire : horairesOptional.get()) {
                HoraireDto horaireDto = new HoraireDto();
                horaireDto.setId(horaire.getId());
                horaireDto.setName(horaire.getName());
                horaireDto.setFrom(horaire.getFrom());
                horaireDto.setTo(horaire.getTo());
                horaireDto.setActivitePiscineId(horaire.getActivitePiscine().getId());
                horaireDto.setBassin(horaire.getBassin());
                horaireDto.setLongueur(horaire.getLongueur());
                horaireDtos.add(horaireDto);
            }
        }

        return horaireDtos;
    }
}
