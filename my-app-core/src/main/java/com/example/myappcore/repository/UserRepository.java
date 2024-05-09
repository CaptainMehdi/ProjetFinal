package com.example.myappcore.repository;

import com.example.myappcore.model.User;
import com.example.myappcore.utils.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT e FROM User e WHERE e.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT e FROM User e WHERE e.role IN :roles")
    Optional<List<User>> findAllByRoleIn(List<Role> roles);

}
