package com.example.myappcore.dto;

import com.example.myappcore.model.Cours;
import com.example.myappcore.model.User;
import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Type;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data

public class CoursDto extends ActivitePiscineDto {

    private Long id;
    private int maxEleves;
    private List<UserDto> eleve;
    private UserDto prof;
    private NiveauDto niveau;

    public CoursDto(Cours cours) {
        super(cours.getId(), cours.getBassin() , cours.getType());
        this.maxEleves = cours.getMaxEleves();
        this.eleve = cours.getEleve() == null ? new ArrayList<>() : cours.getEleve().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        this.prof = cours.getProf() == null ? null : new UserDto(cours.getProf());
        this.niveau = new NiveauDto(cours.getNiveau());
    }

    public CoursDto(Bassin bassin, Type type, int maxEleves, List<UserDto> eleve, UserDto prof, NiveauDto niveau) {
        super(bassin, type);
        this.maxEleves = maxEleves;
        this.eleve = eleve;
        this.prof = prof;
        this.niveau = niveau;
    }

    public CoursDto() {
    }

}