package com.example.myappcore.repository;

import com.example.myappcore.model.Niveau;
import com.example.myappcore.utils.Bassin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {

    @Query("SELECT e FROM Niveau e WHERE e.bassin = ?1")
    Optional<List<Niveau>> getNiveauByBassin(Bassin bassin);
}
