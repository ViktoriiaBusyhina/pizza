package com.example.pizza.enam;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PizzaSizeTest {
    @Test
   void testPizzaSizeDescription_ok() {
        // Arrange
        String expectedKidsDescription = "24 cm";
        String expectedMediumDescription = "26 cm";
        String expectedNormalDescription = "30 cm";
        String expectedBigDescription = "33 cm";
        String expectedXXLDescription = "40 cm";

        // Act
        String actualKidsDescription = PizzaSize.KIDS.getDescription();
        String actualMediumDescription = PizzaSize.MEDIUM.getDescription();
        String actualNormalDescription = PizzaSize.NORMAL.getDescription();
        String actualBigDescription = PizzaSize.BIG.getDescription();
        String actualXXLDescription = PizzaSize.XXL.getDescription();

        // Assert
        assertEquals(expectedKidsDescription, actualKidsDescription);
        assertEquals(expectedMediumDescription, actualMediumDescription);
        assertEquals(expectedNormalDescription, actualNormalDescription);
        assertEquals(expectedBigDescription, actualBigDescription);
        assertEquals(expectedXXLDescription, actualXXLDescription);
    }
}