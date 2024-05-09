package com.example.myappcore.dto;

import com.example.myappcore.model.Niveau;
import com.example.myappcore.model.User;
import com.example.myappcore.utils.Bassin;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NiveauDto{
    private Long id;
    private String requis;
    private String nom;
    private Bassin bassin;

    public NiveauDto(Niveau niveau) {
        this.id = niveau.getId();
        this.requis = niveau.getRequis();
        this.nom = niveau.getNom();
        this.bassin = niveau.getBassin();
    }

    public NiveauDto(String requis, String nom, Bassin bassin) {
        this.requis = requis;
        this.nom = nom;
        this.bassin = bassin;
    }

    public Niveau toEntity() {
        return new Niveau(getRequis(),getNom(),getBassin());
    }
}
