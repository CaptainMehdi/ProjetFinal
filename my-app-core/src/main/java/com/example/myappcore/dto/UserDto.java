package com.example.myappcore.dto;

public class UserDto {
    public String email;
    public String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDto(){}
}
