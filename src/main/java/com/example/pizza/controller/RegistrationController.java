package com.example.pizza.controller;

import com.example.pizza.dto.CustomerRegistrationDto;
import com.example.pizza.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RegistrationController {

    private final RegistrationService registrationService;
    @PostMapping(value = "/registration")
    public ResponseEntity<CustomerRegistrationDto> registerNewCustomer(@RequestBody CustomerRegistrationDto customerRegistrationDto) {
        registrationService.registerNewCustomer(customerRegistrationDto);
        return ResponseEntity.ok().build();
    }
}
