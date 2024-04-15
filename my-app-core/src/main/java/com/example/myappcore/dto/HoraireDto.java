package com.example.myappcore.dto;

import com.example.myappcore.model.ActivitePiscine;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

public class HoraireDto {
    private Long id;

    @Getter
    private String name;

    @Getter
    private Date from;

    @Getter
    private Date to;

    @Getter
    private Long activitePiscineId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public void setActivitePiscineId(Long activitePiscineId) {
        this.activitePiscineId = activitePiscineId;
    }
}
