package com.example.myappcore.dto;

import com.example.myappcore.model.Fichier;

public class FichierDto {
    Long id;

    byte[] data;

    String nom;

    public FichierDto(Fichier fichier) {
        this.id = fichier.getId();
        this.data = fichier.getData();
        this.nom = fichier.getNom();
    }
}
