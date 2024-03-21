package com.example.myappcore.dto;

import com.example.myappcore.model.User;

public class UserDetails {
    public Long id;

    public String email;

    public String password;

    public String lastname;

    public String firstname;

    public UserDetails(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
    }
}
