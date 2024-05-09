package com.example.myappcore.controller;

import com.example.myappcore.dto.UserDto;
import com.example.myappcore.service.UserService;
import com.example.myappcore.utils.Bassin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<UserDto>> getAllProf() {
        return new ResponseEntity<>(userService.getAllTeachers(), HttpStatus.OK);
    }
}
