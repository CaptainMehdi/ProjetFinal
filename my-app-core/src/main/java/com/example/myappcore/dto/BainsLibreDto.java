package com.example.myappcore.dto;

import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Type;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BainsLibreDto extends ActivitePiscineDto{

    private Long id;
    private Bassin bassin;
    private List<UserDto> sauveteurs;
    private String nom;

    public BainsLibreDto(BainsLibre bainsLibre) {
        super(bainsLibre.getId(),bainsLibre.getBassin(),bainsLibre.getType());
        this.sauveteurs = bainsLibre.getSauveteurs() == null ? new  ArrayList<>() : bainsLibre.getSauveteurs().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        this.nom = "Bains Libres";
    }

    public BainsLibreDto(Bassin bassin, Type type, List<UserDto> sauveteurs) {
        super(bassin,type);
        this.sauveteurs = sauveteurs;
        this.nom = "Bains Libres";
    }



}
