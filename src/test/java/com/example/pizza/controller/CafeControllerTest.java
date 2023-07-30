package com.example.pizza.controller;

import com.example.pizza.dto.CafeDto;
import com.example.pizza.entity.Cafe;
import com.example.pizza.service.CafeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CafeControllerTest {

    @Mock
    CafeService cafeService;

    @InjectMocks
    CafeController cafeController;

    @Test
    void createNewCafe_ShouldReturnOk_ok() {
        // Arrange
        CafeDto cafe = new CafeDto();

        // Act
        ResponseEntity<CafeDto> response = cafeController.createNewCafe(cafe);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cafeService, times(1)).createNewCafe(cafe);
    }

    @Test
    void findAllCafes_WithCafes_ShouldReturnOkWithCafes_ok() {
        // Arrange
        List<CafeDto> cafes = new ArrayList<>();
        cafes.add(new CafeDto());

        when(cafeService.findAll()).thenReturn(cafes);

        // Act
        ResponseEntity<List<CafeDto>> response = cafeController.findAllCafes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cafes, response.getBody());
        verify(cafeService, times(1)).findAll();
    }

    @Test
    void findAllCafes_WithNoCafes_ShouldReturnNoContent_ok() {
        // Arrange
        when(cafeService.findAll()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<CafeDto>> response = cafeController.findAllCafes();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cafeService, times(1)).findAll();
    }

    @Test
    void findCafeById_ExistingId_ShouldReturnOkWithCafe_ok() {
        // Arrange
        Integer id = 1;
        CafeDto cafe = new CafeDto();

        when(cafeService.findById(id)).thenReturn(cafe);

        // Act
        ResponseEntity<CafeDto> response = cafeController.findCafeById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cafe, response.getBody());
        verify(cafeService, times(1)).findById(id);
    }

    @Test
    void updateCafe_ExistingId_ShouldReturnOkWithUpdatedCafe_ok() {
        // Arrange
        Integer id = 1;
        CafeDto cafe = new CafeDto();

        // Act
        ResponseEntity<CafeDto> response = cafeController.updateCafe(id, cafe);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cafeService, times(1)).update(id, cafe);
    }

    @Test
    void deleteCafe_ExistingId_ShouldReturnOk_ok() {
// Arrange
        Integer id = 1;

// Act
        ResponseEntity<String> response = cafeController.deleteCafe(id);

// Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cafeService, times(1)).delete(id);
    }
}