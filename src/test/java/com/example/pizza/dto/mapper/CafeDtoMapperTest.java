package com.example.pizza.dto.mapper;

import com.example.pizza.dto.CafeDto;
import com.example.pizza.entity.Cafe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CafeDtoMapperTest {

    CafeDtoMapper cafeDtoMapper = new CafeDtoMapper();

    @Test
    void dtoToEntity_ShouldMapDtoToEntity_ok() {
        // Arrange
        CafeDto cafeDto = new CafeDto();
        cafeDto.setPizzaId(2);
        cafeDto.setName("Cafe Name");
        cafeDto.setAddress("Cafe Address");
        cafeDto.setPhone("Cafe Phone");
        cafeDto.setSchedule("Cafe Schedule");

        // Act
        Cafe result = cafeDtoMapper.dtoToEntity(cafeDto);

        // Assert
        assertEquals(cafeDto.getPizzaId(), result.getPizzaId());
        assertEquals(cafeDto.getName(), result.getName());
        assertEquals(cafeDto.getAddress(), result.getAddress());
        assertEquals(cafeDto.getPhone(), result.getPhone());
        assertEquals(cafeDto.getSchedule(), result.getSchedule());
    }

    @Test
    void entityToDto_ShouldMapEntityToDto_ok() {
        // Arrange
        Cafe cafe = new Cafe();
        cafe.setId(1);
        cafe.setPizzaId(2);
        cafe.setName("Cafe Name");
        cafe.setAddress("Cafe Address");
        cafe.setPhone("Cafe Phone");
        cafe.setSchedule("Cafe Schedule");

        // Act
        CafeDto result = cafeDtoMapper.entityToDto(cafe);

        // Assert
        assertEquals(cafe.getPizzaId(), result.getPizzaId());
        assertEquals(cafe.getName(), result.getName());
        assertEquals(cafe.getAddress(), result.getAddress());
        assertEquals(cafe.getPhone(), result.getPhone());
        assertEquals(cafe.getSchedule(), result.getSchedule());
    }
}