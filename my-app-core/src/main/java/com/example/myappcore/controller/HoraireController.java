package com.example.myappcore.controller;

import com.example.myappcore.dto.FichierDto;
import com.example.myappcore.dto.HoraireDto;
import com.example.myappcore.service.HoraireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<HoraireDto> save(@RequestBody HoraireDto horaireDto) {
        return new ResponseEntity<>(horaireService.save(horaireDto), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HoraireDto>> getAllHoraire(){
        return new ResponseEntity<>(horaireService.getAllHoraire(), HttpStatus.CREATED);
    }

    @PostMapping("/create/file/excel")
    public ResponseEntity<FichierDto> createFile() throws IOException {
        return new ResponseEntity<>(horaireService.createHoraireExcel(), HttpStatus.CREATED);
    }
}
