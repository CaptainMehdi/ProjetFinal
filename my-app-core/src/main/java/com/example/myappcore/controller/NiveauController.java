package com.example.myappcore.controller;

import com.example.myappcore.dto.ActivitePiscineDto;
import com.example.myappcore.dto.NiveauDto;
import com.example.myappcore.service.NiveauService;
import com.example.myappcore.utils.Bassin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/niveau")
@CrossOrigin(origins = "http://localhost:3000")
public class NiveauController {

    private NiveauService niveauService;

    public NiveauController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @GetMapping("/all/{bassin}")
    public ResponseEntity<List<NiveauDto>> getAllNiveaux(@PathVariable Bassin bassin) {
        return new ResponseEntity<>(niveauService.getAllNiveaux(bassin), HttpStatus.OK);
    }
}
