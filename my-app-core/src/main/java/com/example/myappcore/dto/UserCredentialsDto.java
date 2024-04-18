package com.example.myappcore.dto;

public class UserCredentialsDto {
    public String email;
    public String password;

    public UserCredentialsDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserCredentialsDto(){}

}
