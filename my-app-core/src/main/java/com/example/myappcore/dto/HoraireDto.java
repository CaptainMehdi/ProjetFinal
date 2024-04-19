package com.example.myappcore.dto;

import com.example.myappcore.utils.Bassin;
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

    @Override
    public String toString() {
        return "HoraireDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", activitePiscineId=" + activitePiscineId +
                ", bassin=" + bassin +
                ", longueur_utilise=" + longueur +
                '}';
    }
}
