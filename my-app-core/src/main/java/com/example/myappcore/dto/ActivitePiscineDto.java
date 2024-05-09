package com.example.myappcore.dto;

import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Type;

public class ActivitePiscineDto {
    private Long id;
    private Bassin bassin;
    private Type typeActivity;

    public ActivitePiscineDto() {

    }

    public ActivitePiscineDto(Long id, Bassin bassin, Type typeActivity) {
        this.id = id;
        this.bassin = bassin;
        this.typeActivity = typeActivity;
    }

    public ActivitePiscineDto(Bassin bassin, Type typeActivity) {
        this.bassin = bassin;
        this.typeActivity = typeActivity;
    }

    public void setBassin(Bassin bassin) {
        this.bassin = bassin;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
