package com.example.myappcore.dto;

public class UserRegistrationDto {
    public String email;

    public String password;

    public String lastname;

    public String firstname;

    public UserRegistrationDto(String email, String password, String lastname, String firstname) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
    }
}
