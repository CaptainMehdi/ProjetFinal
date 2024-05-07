package com.example.myappcore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Fichier")
@NoArgsConstructor
@AllArgsConstructor
public class Fichier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    byte[] data;

    String nom;

}
