package com.example.myappcore.dto;

import com.example.myappcore.model.Horaire;
import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Jour;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

public class HoraireDto {
    @Setter
    private Long id;

    @Getter @Setter
    private String from;

    @Getter @Setter
    private String to;

    @Getter @Setter
    private Long activitePiscineId;

    @Getter@Setter
    private String nom;

    @Getter @Setter
    private Bassin bassin;

    @Getter @Setter
    private List<Integer> longueur;

    @Getter @Setter
    private Jour day;

    public HoraireDto() {
    }

    public HoraireDto(Horaire horaire) {
        this.id = horaire.getId();
        this.from = horaire.getFrom();
        this.to = horaire.getTo();
        this.activitePiscineId = horaire.getActivitePiscine().getId();
        this.bassin = horaire.getBassin();
        this.longueur = horaire.getLongueur();
        this.day = horaire.getDay();
        this.nom = horaire.getNom();
    }
}
