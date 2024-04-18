package com.example.myappcore.model;

import com.example.myappcore.utils.Bassin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "BainsLibre")
@NoArgsConstructor
@AllArgsConstructor
public class BainsLibre extends ActivitePiscine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> sauveteurs;

    public BainsLibre(Bassin bassin, List<User> sauveteurs) {
        super(bassin);
        this.sauveteurs = sauveteurs;
    }
}

