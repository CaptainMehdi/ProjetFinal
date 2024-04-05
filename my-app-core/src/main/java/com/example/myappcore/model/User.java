package com.example.myappcore.model;

import com.example.myappcore.utils.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "UTILISATEUR")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String email;

    public String password;

    public String lastname;

    public String firstname;

    public Role role;

    public User(String email, String correctPassword) {
    }

    public User(String email, String password, String lastname, String firstname, Role role) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
    }
}
