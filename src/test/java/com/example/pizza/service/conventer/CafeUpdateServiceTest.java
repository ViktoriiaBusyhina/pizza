package com.example.pizza.service.conventer;

import com.example.pizza.entity.Cafe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CafeUpdateServiceTest {
    @Test
    void testConvert_withUpdates_shouldUpdateCafeObject_ok() {
        // Arrange
        Cafe cafe = new Cafe();
        cafe.setName("Old Name");
        cafe.setPhone("Old Phone");
        cafe.setAddress("Old Address");
        cafe.setSchedule("Old Schedule");

        Cafe cafeUpdate = new Cafe();
        cafeUpdate.setName("New Name");
        cafeUpdate.setPhone("New Phone");

        CafeUpdateService cafeUpdateService = new CafeUpdateService();

        // Act
        Cafe updatedCafe = cafeUpdateService.convert(cafe, cafeUpdate);

        // Assert
        assertEquals("New Name", updatedCafe.getName());
        assertEquals("New Phone", updatedCafe.getPhone());
        assertEquals("Old Address", updatedCafe.getAddress());
        assertEquals("Old Schedule", updatedCafe.getSchedule());
    }

    @Test
     void testConvert_withNullUpdates_shouldNotUpdateCafeObject_ok() {
        // Arrange
        Cafe cafe = new Cafe();
        cafe.setName("Old Name");
        cafe.setPhone("Old Phone");
        cafe.setAddress("Old Address");
        cafe.setSchedule("Old Schedule");

        Cafe cafeUpdate = new Cafe(); // All attributes are null

        CafeUpdateService cafeUpdateService = new CafeUpdateService();

        // Act
        Cafe updatedCafe = cafeUpdateService.convert(cafe, cafeUpdate);

        // Assert
        assertEquals("Old Name", updatedCafe.getName());
        assertEquals("Old Phone", updatedCafe.getPhone());
        assertEquals("Old Address", updatedCafe.getAddress());
        assertEquals("Old Schedule", updatedCafe.getSchedule());
    }
}