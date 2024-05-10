package com.example.myappcore.controller;

import com.example.myappcore.dto.ActivitePiscineDto;
import com.example.myappcore.dto.BainsLibreDto;
import com.example.myappcore.service.ActivitePiscineService;
import com.example.myappcore.service.BainsLibreService;
import com.example.myappcore.utils.Bassin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/activity")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivitePiscineController {
    private ActivitePiscineService activitePiscineService;
    private BainsLibreService bainsLibreService;

    public ActivitePiscineController(ActivitePiscineService activitePiscineService, BainsLibreService bainsLibreService) {
        this.activitePiscineService = activitePiscineService;
        this.bainsLibreService = bainsLibreService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActivitePiscineDto>> getAllActivities() {
        return new ResponseEntity<>(activitePiscineService.getAllActivities(), HttpStatus.OK);
    }

    @GetMapping("/get/{bassin}")
    public ResponseEntity<Long> getBainsLibresId(@PathVariable Bassin bassin) {
        return new ResponseEntity<>(bainsLibreService.getBainsLibresId(bassin), HttpStatus.OK);
    }
}
