package com.example.myappcore.model;

import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Jour;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Horaire")
@NoArgsConstructor
@AllArgsConstructor
public class Horaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "\"from\"")
    private String from;

    @Column(name = "\"to\"")
    private String to;

    @ManyToOne
    @JoinColumn(name = "activite_piscine_id")
    private ActivitePiscine activitePiscine;

    private Bassin bassin;

    @ElementCollection
    @CollectionTable(name = "longueur", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "longueur")
    private List<Integer> longueur;

    private Jour day;

}