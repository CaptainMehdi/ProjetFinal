package com.example.myappcore.repository;

import com.example.myappcore.model.BainsLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BainsLibreRepository extends JpaRepository<BainsLibre,Long> {
}
