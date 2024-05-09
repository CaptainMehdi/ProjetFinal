package com.example.myappcore.service;

import com.example.myappcore.dto.CoursAjoutDto;
import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.dto.UserDto;
import com.example.myappcore.model.Cours;
import com.example.myappcore.model.Niveau;
import com.example.myappcore.model.User;
import com.example.myappcore.repository.CoursRepository;
import com.example.myappcore.repository.NiveauRepository;
import com.example.myappcore.repository.UserRepository;
import com.example.myappcore.utils.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoursService {

    private final CoursRepository coursRepository;
    private final NiveauRepository niveauRepository;

    private final UserRepository userRepository;

    public CoursService(CoursRepository coursRepository, NiveauRepository niveauRepository, UserRepository userRepository) {
        this.coursRepository = coursRepository;
        this.niveauRepository = niveauRepository;
        this.userRepository = userRepository;
    }

    public Long saveCours(CoursAjoutDto coursDto) {
        if(coursDto != null){
            Cours cours = new Cours();
            Optional<Niveau> existingNiveauOptional = niveauRepository.findById(coursDto.getNiveau().getId());
            cours.setNiveau(existingNiveauOptional.isPresent()?existingNiveauOptional.get():null);

            Optional<User> existingUserOptional = userRepository.findById(coursDto.getProf().getId());
            cours.setProf(existingUserOptional.isPresent()?existingUserOptional.get():null);

            cours.setType(Type.cours);
            Cours savedCours = coursRepository.save(cours);

            return savedCours.getId();
        }
        throw new RuntimeException();
    }

    public Optional<CoursDto> getCoursById(Long id) {
        Optional<Cours> coursOptional = coursRepository.findById(id);
        return coursOptional.map(CoursDto::new);
    }
}