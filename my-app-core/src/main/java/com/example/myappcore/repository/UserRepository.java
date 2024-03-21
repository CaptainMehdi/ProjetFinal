package com.example.myappcore.repository;

import com.example.myappcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT e FROM User e WHERE e.email = ?1")
    Optional<User> findByEmail(String email);

}
