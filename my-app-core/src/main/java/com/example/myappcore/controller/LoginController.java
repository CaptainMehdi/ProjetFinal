package com.example.myappcore.controller;

import com.example.myappcore.dto.UserDetails;
import com.example.myappcore.dto.UserDto;
import com.example.myappcore.dto.UserRegistrationDto;
import com.example.myappcore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(loginService.login(userDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserRegistrationDto userRegistrationDto){
        return new ResponseEntity<>(loginService.register(userRegistrationDto), HttpStatus.CREATED);
    }
}
