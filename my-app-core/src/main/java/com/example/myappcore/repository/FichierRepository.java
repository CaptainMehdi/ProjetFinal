package com.example.myappcore.repository;

import com.example.myappcore.model.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichierRepository extends JpaRepository<Fichier, Long> {
}
