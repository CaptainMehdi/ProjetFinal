package com.example.myappcore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.myappcore.dto.ActivitePiscineDto;
import com.example.myappcore.model.ActivitePiscine;
import com.example.myappcore.repository.ActivitePiscineRepository;
import com.example.myappcore.repository.BainsLibreRepository;
import com.example.myappcore.repository.CoursRepository;
import com.example.myappcore.repository.NiveauRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ActivitePiscineServiceTest {

    private ActivitePiscineRepository activitePiscineRepository;
    private BainsLibreRepository bainsLibreRepository;
    private NiveauRepository niveauRepository;

    private ActivitePiscineService activitePiscineService;

    @BeforeEach
    void setUp() {
        activitePiscineRepository = mock(ActivitePiscineRepository.class);
        bainsLibreRepository = mock(BainsLibreRepository.class);
        niveauRepository = mock(NiveauRepository.class);

        activitePiscineService = new ActivitePiscineService(
                activitePiscineRepository,
                bainsLibreRepository,
                niveauRepository
        );
    }

    @Test
    void getAllActivities_ReturnsCombinedList_WhenRepositoriesHaveData() {
        List<ActivitePiscineDto> expectedActivities = new ArrayList<>();
        when(niveauRepository.findAll()).thenReturn(new ArrayList<>());
        when(bainsLibreRepository.findAll()).thenReturn(new ArrayList<>());

        List<ActivitePiscineDto> actualActivities = activitePiscineService.getAllActivities();

        assertEquals(expectedActivities, actualActivities);
    }

    @Test
    void getAllActivities_ReturnsEmptyList_WhenRepositoriesAreEmpty() {
        when(niveauRepository.findAll()).thenReturn(new ArrayList<>());
        when(bainsLibreRepository.findAll()).thenReturn(new ArrayList<>());

        List<ActivitePiscineDto> actualActivities = activitePiscineService.getAllActivities();

        assertTrue(actualActivities.isEmpty());
    }

    @Test
    void getActivitiesById_ReturnsCorrectActivity_WhenIdExists() {
        Long id = 1L;
        ActivitePiscine expectedActivity = new ActivitePiscine();
        when(activitePiscineRepository.getById(id)).thenReturn(expectedActivity);

        ActivitePiscine actualActivity = activitePiscineService.getActivitiesById(id);

        assertEquals(expectedActivity, actualActivity);
    }

    @Test
    void getActivitiesById_ReturnsNull_WhenIdDoesNotExist() {
        Long id = 1L;
        when(activitePiscineRepository.getById(id)).thenReturn(null);

        ActivitePiscine actualActivity = activitePiscineService.getActivitiesById(id);

        assertNull(actualActivity);
    }

    @Test
    void getActivitiesById_ReturnsNull_WhenIdIsNull() {
        ActivitePiscine actualActivity = activitePiscineService.getActivitiesById(null);

        assertNull(actualActivity);
    }
}
