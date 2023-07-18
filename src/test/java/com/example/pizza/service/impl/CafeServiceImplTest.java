package com.example.pizza.service.impl;

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

    @InjectMocks
    CafeServiceImpl cafeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createNewCafe_ok() {
        // given
        Cafe cafe = new Cafe();

        // when
        cafeService.createNewCafe(cafe);

        // then
        verify(cafeRepository).save(cafe);
    }

    @Test
    void findAll_ok() {
        // given
        List<Cafe> expected = List.of(new Cafe(), new Cafe());
        when(cafeRepository.findAll()).thenReturn(expected);

        // when
        List<Cafe> actual = cafeService.findAll();

        // then
        assertEquals(expected, actual);
        verify(cafeRepository).findAll();
    }

    @Test
    void findById_ok() {
        // given
        Cafe expected = new Cafe();
        Integer id = 1;
        when(cafeRepository.findById(id)).thenReturn(Optional.of(expected));

        // when
        Cafe actual = cafeService.findById(id);

        // then
        assertEquals(expected, actual);
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
        Cafe updatedCafe = new Cafe();

        when(cafeRepository.findById(id)).thenReturn(Optional.of(existingCafe));
        when(cafeUpdateService.convert(existingCafe, updatedCafe)).thenReturn(updatedCafe);
        when(cafeRepository.save(updatedCafe)).thenReturn(updatedCafe);

        // Act
        Cafe result = cafeService.update(id, updatedCafe);

        // Assert
        assertEquals(updatedCafe, result);
        verify(cafeRepository, times(1)).save(updatedCafe);
    }

    @Test
    void update_NonExistingId_ShouldThrowDataNotFoundException_ok() {
        // Arrange
        Integer id = 1;
        Cafe updatedCafe = new Cafe();

        when(cafeRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> cafeService.update(id, updatedCafe));
        verify(cafeRepository, never()).save(updatedCafe);
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