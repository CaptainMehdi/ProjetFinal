package com.example.myappcore.dto;

import com.example.myappcore.model.ActivitePiscine;
import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.model.Cours;
import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Jour;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class ActivitePiscineDto {
    private Long id;
    private Bassin bassin;

    public ActivitePiscineDto() {

    }

    public ActivitePiscineDto(Long id, Bassin bassin) {
        this.id = id;
        this.bassin = bassin;
    }

    public void setBassin(Bassin bassin) {
        this.bassin = bassin;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
