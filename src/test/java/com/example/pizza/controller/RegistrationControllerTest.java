package com.example.pizza.controller;

import com.example.pizza.dto.CustomerRegistrationDto;
import com.example.pizza.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {
    @Mock
    RegistrationService registrationService;
    @InjectMocks
    RegistrationController registrationController;
    @Test
    public void testRegisterNewCustomer_ok
            () {

        // Create a mock CustomerRegistrationDto
        CustomerRegistrationDto customerRegistrationDto = new CustomerRegistrationDto();
        // Set customerRegistrationDto properties if needed

        // Call the registerNewCustomer method
        ResponseEntity<CustomerRegistrationDto> responseEntity = registrationController.registerNewCustomer(customerRegistrationDto);

        // Verify that the registrationService.registerNewCustomer method is called with the correct customerRegistrationDto
        verify(registrationService, times(1)).registerNewCustomer(customerRegistrationDto);

        // Assert that the response entity has an HTTP status of OK (200)
        assert responseEntity != null;
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }
}