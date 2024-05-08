package com.example.myappcore.model;

import com.example.myappcore.utils.Bassin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Niveau")
@NoArgsConstructor
@AllArgsConstructor
public class Niveau extends ActivitePiscine{
    @Id
    @GeneratedValue
    private Long id;
    private String requis;
    private String nom;

    public Niveau(String requis, String nom, Bassin bassin) {
        super(bassin);
        this.requis = requis;
        this.nom = nom;
    }
}
