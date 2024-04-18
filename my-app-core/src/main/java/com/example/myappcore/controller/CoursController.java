package com.example.myappcore.controller;

import com.example.myappcore.service.CoursService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/cours")
@CrossOrigin(origins = "http://localhost:3000")
public class CoursController {
    private CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }


}
