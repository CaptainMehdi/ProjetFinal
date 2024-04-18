package com.example.myappcore.model;

import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Jour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity
@Data
@Table(name = "Cours")
@NoArgsConstructor
@AllArgsConstructor
public class Cours extends ActivitePiscine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int maxEleves;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> eleve;

    @ManyToOne(cascade = CascadeType.ALL)
    private User prof;

    private String requis;

    private String nom;

    public Cours(Bassin bassin, int maxEleves, List<User> eleve, User prof, String requis, String nom) {
        super(bassin);
        this.maxEleves = maxEleves;
        this.eleve = eleve;
        this.prof = prof;
        this.requis = requis;
        this.nom = nom;
    }
}
