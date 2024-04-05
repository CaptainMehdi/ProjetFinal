package com.example.myappcore.controller;

import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.dto.UserDetails;
import com.example.myappcore.dto.UserDto;
import com.example.myappcore.service.CoursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CoursController {
    private CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }


}
