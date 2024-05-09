package com.example.myappcore.controller;

import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class EnumController {

    @GetMapping("/enumBassin")
    public ResponseEntity<List<String>> getBassinEnumValues() {
        List<String> enumValues = Arrays.stream(Bassin.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(enumValues);
    }

    @GetMapping("/enumType")
    public ResponseEntity<List<String>> getTypeEnumsValues() {
        List<String> enumValues = Arrays.stream(Type.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(enumValues);
    }
}