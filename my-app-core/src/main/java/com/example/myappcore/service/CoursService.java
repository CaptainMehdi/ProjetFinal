package com.example.myappcore.service;

import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.dto.UserDto;
import com.example.myappcore.model.Cours;
import com.example.myappcore.model.User;
import com.example.myappcore.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoursService {

    private final CoursRepository coursRepository;

    @Autowired
    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public CoursDto saveCours(CoursDto coursDto) {
        if(coursDto != null){
            Cours cours = new Cours();
            cours.setMaxEleves(coursDto.getMaxEleves());
            List<User> eleves = coursDto.getEleve() == null ? new ArrayList<>() :
                    coursDto.getEleve().stream().map(UserDto::toEntity).collect(Collectors.toList());
            cours.setEleve(eleves);

            User prof = coursDto.getProf() == null ? null : coursDto.getProf().toEntity();
            cours.setProf(prof);

            cours.setNiveau(coursDto.getNiveau().toEntity());

            Cours savedCours = coursRepository.save(cours);
            return new CoursDto(savedCours);
        }
        return null;
    }

    public Optional<CoursDto> getCoursById(Long id) {
        Optional<Cours> coursOptional = coursRepository.findById(id);
        return coursOptional.map(CoursDto::new);
    }
}