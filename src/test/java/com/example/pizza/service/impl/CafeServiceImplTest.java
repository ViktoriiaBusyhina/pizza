package com.example.pizza.service.impl;

import com.example.pizza.dto.CafeDto;
import com.example.pizza.dto.mapper.CafeDtoMapper;
import com.example.pizza.entity.Cafe;
import com.example.pizza.exception.DataNotFoundException;
import com.example.pizza.repository.CafeRepository;
import com.example.pizza.service.conventer.CafeUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CafeServiceImplTest {

    @Mock
    CafeRepository cafeRepository;
    @Mock
    CafeUpdateService cafeUpdateService;
    @Mock
    CafeDtoMapper cafeDtoMapper;

    @InjectMocks
    CafeServiceImpl cafeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createNewCafe_ok() {
        // given
        CafeDto cafeDto = new CafeDto();
        Cafe cafe = new Cafe();
        when(cafeDtoMapper.dtoToEntity(cafeDto)).thenReturn(cafe);

        // when
        cafeService.createNewCafe(cafeDto);

        // then
        verify(cafeDtoMapper).dtoToEntity(cafeDto);
        verify(cafeRepository).save(cafe);
    }

    @Test
    void findAll_ok() {
        // given
        Cafe cafe = new Cafe();
        CafeDto cafeDto = new CafeDto();
        List<Cafe> cafes = List.of(cafe);
        List<CafeDto> expected = List.of(cafeDto);
        when(cafeRepository.findAll()).thenReturn(cafes);
        when(cafeDtoMapper.entityToDto(cafe)).thenReturn(cafeDto);

        // when
        List<CafeDto> actual = cafeService.findAll();

        // then
        assertEquals(expected, actual);
        verify(cafeDtoMapper).entityToDto(cafe);
        verify(cafeRepository).findAll();
    }

    @Test
    void findById_ok() {
        // given
        CafeDto expected = new CafeDto();
        Cafe cafe = new Cafe();
        Integer id = 1;
        when(cafeRepository.findById(id)).thenReturn(Optional.of(cafe));
        when(cafeDtoMapper.entityToDto(cafe)).thenReturn(expected);

        // when
        CafeDto actual = cafeService.findById(id);

        // then
        assertEquals(expected, actual);
        verify(cafeDtoMapper).entityToDto(cafe);
        verify(cafeRepository).findById(id);
    }

    @Test
    void findById_throwsException_ok() {
        Integer id = 1;
        assertThrows(DataNotFoundException.class, () -> cafeService.findById(id));
    }

    @Test
    void update_ok() {
        // Arrange
        Integer id = 1;
        Cafe existingCafe = new Cafe();
        CafeDto updatedCafe = new CafeDto();
        Cafe mappedCafe = new Cafe();

        when(cafeRepository.findById(id)).thenReturn(Optional.of(existingCafe));
        when(cafeDtoMapper.dtoToEntity(updatedCafe)).thenReturn(mappedCafe);
        when(cafeUpdateService.convert(existingCafe, mappedCafe)).thenReturn(mappedCafe);

        // Act
        cafeService.update(id, updatedCafe);

        // Assert
        verify(cafeRepository).findById(id);
        verify(cafeDtoMapper).dtoToEntity(updatedCafe);
        verify(cafeRepository, times(1)).save(mappedCafe);
    }

    @Test
    void update_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;
        CafeDto updatedCafe = new CafeDto();

        when(cafeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> cafeService.update(id, updatedCafe));
        verify(cafeRepository, never()).save(any(Cafe.class));
    }


    @Test
    void delete_ShouldDeleteCafe_ok() {
        // Arrange
        Integer id = 1;

        // Act
        cafeService.delete(id);

        // Assert
        verify(cafeRepository, times(1)).deleteById(id);
    }
}