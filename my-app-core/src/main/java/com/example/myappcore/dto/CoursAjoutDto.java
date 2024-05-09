package com.example.myappcore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CoursAjoutDto {
    private UserDto prof;
    private NiveauDto niveau;
}
