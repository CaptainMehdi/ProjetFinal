package com.example.myappcore.dto;

import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Jour;
import lombok.Getter;

import java.util.List;

public class HoraireDto {
    private Long id;

    @Getter
    private String name;

    @Getter
    private String from;

    @Getter
    private String to;

    @Getter
    private Long activitePiscineId;

    @Getter
    private Bassin bassin;

    @Getter
    private List<Integer> longueur;

    @Getter
    private Jour day;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setActivitePiscineId(Long activitePiscineId) {
        this.activitePiscineId = activitePiscineId;
    }

    public void setBassin(Bassin bassin) {
        this.bassin = bassin;
    }

    public void setLongueur(List<Integer> longueur) {
        this.longueur = longueur;
    }

}
