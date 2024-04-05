package com.example.myappcore.dto;

import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Jour;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Date;

public class ActivitePiscineDto {
    private Long id;
    private Bassin bassin;
    private Jour jour;
    private Date fromDate;
    private Date toDate;

    public void setBassin(Bassin bassin) {
        this.bassin = bassin;
    }

    public void setJour(Jour jour) {
        this.jour = jour;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
}
