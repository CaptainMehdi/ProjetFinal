package com.example.myappcore.repository;

import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.utils.Bassin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BainsLibreRepository extends JpaRepository<BainsLibre,Long> {

    @Query("SELECT e.id FROM BainsLibre e WHERE e.bassin = ?1")
    Optional<Long> getBainsLibreIdByBassin(Bassin bassin);
}
