package com.example.myappcore.repository;

import com.example.myappcore.model.Horaire;
import com.example.myappcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Long> {

}
