package com.example.myappcore.repository;

import com.example.myappcore.model.ActivitePiscine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitePiscineRepository extends JpaRepository<ActivitePiscine, Long> {
}
