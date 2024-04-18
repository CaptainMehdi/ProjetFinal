package com.example.myappcore.dto;

import com.example.myappcore.model.User;
import com.example.myappcore.utils.Role;
import lombok.Data;

@Data
public class UserDto {
    public Long id;

    public String email;

    public String password;

    public String lastname;

    public String firstname;

    public Role role;



    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.role = user.getRole();
    }

    public UserDto(String email, String password, String lastname, String firstname, Role role) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
    }


    public User toEntity() {
        return new User(getEmail(),getPassword(),getFirstname(),getFirstname(),getRole());
    }
}
