package com.example.myappcore.dto;

import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.utils.Bassin;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BainsLibreDto extends ActivitePiscineDto{

    private Long id;
    private Bassin bassin;
    private List<UserDto> sauveteurs;

    public BainsLibreDto(BainsLibre bainsLibre) {
        this.id = bainsLibre.getId();
        this.bassin = bainsLibre.getBassin();

        // Check if bainsLibre.getSauveteurs() is not null before mapping
        this.sauveteurs = bainsLibre.getSauveteurs() == null ? new  ArrayList<>() : bainsLibre.getSauveteurs().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public BainsLibreDto(Bassin bassin, List<UserDto> sauveteurs) {
        this.bassin = bassin;
        this.sauveteurs = sauveteurs;
    }

}
