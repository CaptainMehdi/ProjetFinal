package com.example.myappcore.controller;

import com.example.myappcore.dto.HoraireDto;
import com.example.myappcore.service.HoraireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/horaire")
@CrossOrigin(origins = "http://localhost:3000")
public class HoraireController {
    private HoraireService horaireService;

    public HoraireController(HoraireService horaireService) {
        this.horaireService = horaireService;
    }

    @PostMapping("/save")
    public ResponseEntity<HoraireDto> save(@RequestBody HoraireDto userDto) {
        return new ResponseEntity<>(horaireService.save(userDto), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HoraireDto>> getAllHoraire(){
        return new ResponseEntity<>(horaireService.getAllHoraire(), HttpStatus.CREATED);
    }
}
