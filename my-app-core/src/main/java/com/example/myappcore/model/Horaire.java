package com.example.myappcore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private Date from;

    @Column(name = "\"to\"")
    private Date to;

    @OneToOne
    @JoinColumn(name = "activite_piscine_id")
    private ActivitePiscine activitePiscine;

}