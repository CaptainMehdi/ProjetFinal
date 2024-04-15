package com.example.myappcore.controller;

import com.example.myappcore.dto.ActivitePiscineDto;
import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.dto.UserDto;
import com.example.myappcore.service.ActivitePiscineService;
import com.example.myappcore.service.CoursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/activity")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivitePiscineController {
    private ActivitePiscineService activitePiscineService;

    public ActivitePiscineController(ActivitePiscineService activitePiscineService) {
        this.activitePiscineService = activitePiscineService;
    }

    @GetMapping("/activities")
    public ResponseEntity<List<ActivitePiscineDto>> getAllCours(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(activitePiscineService.getAllActivities(), HttpStatus.OK);
    }
}
