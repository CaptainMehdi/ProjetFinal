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
public class Cours{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int maxEleves;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> eleve;

    @ManyToOne(cascade = CascadeType.ALL)
    private User prof;

    @ManyToOne
    private Niveau niveau;

    public Cours(int maxEleves, List<User> eleve, User prof, Niveau niveau) {
        this.maxEleves = maxEleves;
        this.eleve = eleve;
        this.prof = prof;
        this.niveau = niveau;
    }
}
