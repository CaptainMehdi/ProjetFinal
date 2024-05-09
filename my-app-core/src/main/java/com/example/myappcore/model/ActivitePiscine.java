package com.example.myappcore.model;

import com.example.myappcore.utils.Bassin;
import com.example.myappcore.utils.Jour;
import com.example.myappcore.utils.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "Activite")
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class ActivitePiscine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Bassin bassin;

    @Enumerated(EnumType.STRING)
    private Type type;

    public ActivitePiscine() {
    }

    public ActivitePiscine(Bassin bassin, Type type) {
        this.bassin = bassin;
        this.type = type;
    }
}

