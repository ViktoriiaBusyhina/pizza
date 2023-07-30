package com.example.pizza.controller;

import com.example.pizza.dto.CustomerRegistrationDto;
import com.example.pizza.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controller class for customer registration.
 * This class handles incoming HTTP requests related to customer registration.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Handles the HTTP POST request for customer registration.
     *
     * @param customerRegistrationDto The DTO containing customer registration details.
     * @return ResponseEntity with HTTP status 200 (OK) if registration is successful.
     */
    @PostMapping(value = "/registration")
    public ResponseEntity<CustomerRegistrationDto> registerNewCustomer(@RequestBody CustomerRegistrationDto customerRegistrationDto) {
        registrationService.registerNewCustomer(customerRegistrationDto);
        return ResponseEntity.ok().build();
    }
}
