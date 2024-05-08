package com.example.myappcore.service;

import com.example.myappcore.dto.HoraireDto;
import com.example.myappcore.model.ActivitePiscine;
import com.example.myappcore.model.Horaire;
import com.example.myappcore.repository.HoraireRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HoraireServiceTest {

    @Mock
    private HoraireRepository horaireRepository;

    @Mock
    private ActivitePiscineService activitePiscineService;

    @InjectMocks
    private HoraireService horaireService;

    @Test
    void save_ReturnsNull_WhenHoraireDtoIsNull() {
        HoraireDto nullDto = null;
        HoraireDto resultDto = horaireService.save(nullDto);
        assertNull(resultDto);
    }

    @Test
    void save_ReturnsHoraireDto_WhenHoraireDtoIsNotNull() {
        HoraireDto inputDto = new HoraireDto();
        inputDto.setName("Test Horaire");
        inputDto.setFrom("09:00");
        inputDto.setTo("11:00");
        inputDto.setActivitePiscineId(1L);

        when(activitePiscineService.getActivitiesById(anyLong())).thenReturn(new ActivitePiscine());
        when(horaireRepository.save(any(Horaire.class))).thenReturn(new Horaire());

        HoraireDto resultDto = horaireService.save(inputDto);

        assertNotNull(resultDto);
        assertEquals(inputDto.getName(), resultDto.getName());
        assertEquals(inputDto.getFrom(), resultDto.getFrom());
        assertEquals(inputDto.getTo(), resultDto.getTo());
        assertEquals(inputDto.getActivitePiscineId(), resultDto.getActivitePiscineId());
    }

    @Test
    void getAllHoraire_ReturnsEmptyList_WhenRepositoryReturnsEmptyOptional() {
        List<HoraireDto> result = horaireService.getAllHoraire();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
