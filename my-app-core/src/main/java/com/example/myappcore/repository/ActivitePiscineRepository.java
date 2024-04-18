package com.example.myappcore.repository;

import com.example.myappcore.model.ActivitePiscine;
import com.example.myappcore.model.User;
import com.example.myappcore.utils.Bassin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivitePiscineRepository extends JpaRepository<ActivitePiscine, Long> {

    @Query("SELECT e.bassin FROM ActivitePiscine e WHERE e.id = ?1")
    Optional<Bassin> getBassinById(Long id);
}
