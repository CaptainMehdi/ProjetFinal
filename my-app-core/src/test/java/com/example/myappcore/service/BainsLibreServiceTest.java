package com.example.myappcore.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.myappcore.dto.BainsLibreDto;
import com.example.myappcore.model.BainsLibre;
import com.example.myappcore.repository.BainsLibreRepository;
import com.example.myappcore.utils.Bassin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class BainsLibreServiceTest {

    private BainsLibreRepository bainsLibreRepository;
    private BainsLibreService bainsLibreService;

    @BeforeEach
    void setUp() {
        bainsLibreRepository = mock(BainsLibreRepository.class);
        bainsLibreService = new BainsLibreService(bainsLibreRepository);
    }

//    @Test
//    void saveBainsLibre_ReturnsSavedBainsLibreDto_WhenValidInput() {
//        BainsLibreDto inputDto = new BainsLibreDto(Bassin.grand, new ArrayList<>());
//        inputDto.setId(1L);
//        BainsLibre savedBainsLibre = new BainsLibre();
//        savedBainsLibre.setId(1L);
//        savedBainsLibre.setBassin(Bassin.grand);
//        when(bainsLibreRepository.save(any(BainsLibre.class))).thenReturn(savedBainsLibre);
//
//        BainsLibreDto resultDto = bainsLibreService.saveBainsLibre(inputDto);
//
//        assertEquals(inputDto, resultDto);
//    }

    @Test
    void saveBainsLibre_ReturnsNull_WhenInputIsNull() {
        BainsLibreDto resultDto = bainsLibreService.saveBainsLibre(null);

        assertNull(resultDto);
    }

    @Test
    void getBainsLibreById_ReturnsBainsLibreDto_WhenIdExists() {
        Long id = 1L;
        BainsLibre bainsLibre = new BainsLibre();
        bainsLibre.setId(id);
        bainsLibre.setBassin(Bassin.grand);
        when(bainsLibreRepository.findById(id)).thenReturn(Optional.of(bainsLibre));

        Optional<BainsLibreDto> resultDto = bainsLibreService.getBainsLibreById(id);

        assertTrue(resultDto.isPresent());
        assertEquals(bainsLibre.getId(), resultDto.get().getId());
        assertEquals(bainsLibre.getBassin(), resultDto.get().getBassin());
    }

    @Test
    void getBainsLibreById_ReturnsEmptyOptional_WhenIdDoesNotExist() {
        Long id = 1L;
        when(bainsLibreRepository.findById(id)).thenReturn(Optional.empty());

        Optional<BainsLibreDto> resultDto = bainsLibreService.getBainsLibreById(id);

        assertTrue(resultDto.isEmpty());
    }

    @Test
    void getBainsLibreById_ReturnsEmptyOptional_WhenIdIsNull() {
        Optional<BainsLibreDto> resultDto = bainsLibreService.getBainsLibreById(null);

        assertTrue(resultDto.isEmpty());
    }
}
