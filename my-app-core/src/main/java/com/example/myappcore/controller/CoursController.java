package com.example.myappcore.controller;

import com.example.myappcore.dto.CoursAjoutDto;
import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.dto.HoraireDto;
import com.example.myappcore.service.CoursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody CoursAjoutDto coursAjoutDto) {
        return new ResponseEntity<>(coursService.saveCours(coursAjoutDto), HttpStatus.CREATED);
    }

}
