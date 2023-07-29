package com.example.pizza.controller;

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
/**

 The CafeControllerTest class is a unit test class for CafeController.

 It tests the methods in CafeController for handling cafe-related operations.
 */
@ExtendWith(MockitoExtension.class)
class CafeControllerTest {

    @Mock
    CafeService cafeService;

    @InjectMocks
    CafeController cafeController;

    /**

     Tests the createNewCafe method in CafeController.

     It asserts that a ResponseEntity with status code HttpStatus.OK is returned.

     It also verifies that the createNewCafe method in CafeService is called once with the specified cafe.
     */
    @Test
    void createNewCafe_ShouldReturnOk_ok() {
// Arrange
        Cafe cafe = new Cafe();

// Act
        ResponseEntity<Cafe> response = cafeController.createNewCafe(cafe);

// Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(cafeService, times(1)).createNewCafe(cafe);
    }

    /**

     Tests the findAllCafes method in CafeController when cafes are present.

     It asserts that a ResponseEntity with status code HttpStatus.OK is returned.

     It also asserts that the response body contains the list of cafes returned by CafeService.

     It verifies that the findAll method in CafeService is called once.
     */
    @Test
    void findAllCafes_WithCafes_ShouldReturnOkWithCafes_ok() {
// Arrange
        List<Cafe> cafes = new ArrayList<>();
        cafes.add(new Cafe());

        when(cafeService.findAll()).thenReturn(cafes);

// Act
        ResponseEntity<List<Cafe>> response = cafeController.findAllCafes();

// Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cafes, response.getBody());
        verify(cafeService, times(1)).findAll();
    }

    /**

     Tests the findAllCafes method in CafeController when no cafes are present.

     It asserts that a ResponseEntity with status code HttpStatus.NO_CONTENT is returned.

     It verifies that the findAll method in CafeService is called once.
     */
    @Test
    void findAllCafes_WithNoCafes_ShouldReturnNoContent_ok() {
// Arrange
        when(cafeService.findAll()).thenReturn(new ArrayList<>());

// Act
        ResponseEntity<List<Cafe>> response = cafeController.findAllCafes();

// Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(cafeService, times(1)).findAll();
    }

    /**

     Tests the findCafeById method in CafeController for an existing cafe id.

     It asserts that a ResponseEntity with status code HttpStatus.OK is returned.

     It also asserts that the response body contains the cafe returned by CafeService.

     It verifies that the findById method in CafeService is called once with the specified id.
     */
    @Test
    void findCafeById_ExistingId_ShouldReturnOkWithCafe_ok() {
// Arrange
        Integer id = 1;
        Cafe cafe = new Cafe();
        cafe.setId(id);

        when(cafeService.findById(id)).thenReturn(cafe);

// Act
        ResponseEntity<Cafe> response = cafeController.findCafeById(id);

// Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cafe, response.getBody());
        verify(cafeService, times(1)).findById(id);
    }

    /**

     Tests the updateCafe method in CafeController for an existing cafe id.

     It asserts that a ResponseEntity with status code HttpStatus.OK is returned.

     It also asserts that the response body contains the updated cafe returned by CafeService.

     It verifies that the update method in CafeService is called once with the specified id and cafe.
     */
    @Test
    void updateCafe_ExistingId_ShouldReturnOkWithUpdatedCafe_ok() {
// Arrange
        Integer id = 1;
        Cafe cafe = new Cafe();
        cafe.setId(id);

        when(cafeService.update(id, cafe)).thenReturn(cafe);

// Act
        ResponseEntity<Cafe> response = cafeController.updateCafe(id, cafe);

// Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cafe, response.getBody());
        verify(cafeService, times(1)).update(id, cafe);
    }

    /**

     Tests the updateCafe method in CafeController for a non-existing cafe id.

     It asserts that a ResponseEntity with status code HttpStatus.NOT_FOUND is returned.

     It verifies that the update method in CafeService is called once with the specified id and cafe.
     */
    @Test
    void updateCafe_NonExistingId_ShouldReturnNotFound_ok() {
// Arrange
        Integer id = 1;
        Cafe cafe = new Cafe();

        when(cafeService.update(id, cafe)).thenReturn(null);

// Act
        ResponseEntity<Cafe> response = cafeController.updateCafe(id, cafe);

// Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(cafeService, times(1)).update(id, cafe);
    }

    /**

     Tests the deleteCafe method in CafeController for an existing cafe id.

     It asserts that a ResponseEntity with status code HttpStatus.OK is returned.

     It verifies that the delete method in CafeService is called once with the specified id.
     */
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