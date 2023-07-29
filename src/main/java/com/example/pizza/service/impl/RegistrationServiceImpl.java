package com.example.pizza.service.impl;

import com.example.pizza.dto.CustomerRegistrationDto;
import com.example.pizza.dto.mapper.CustomerRegistrationDtoMapper;
import com.example.pizza.enam.PaymentMethod;
import com.example.pizza.enam.Roles;
import com.example.pizza.entity.Customer;
import com.example.pizza.service.CustomerService;
import com.example.pizza.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final CustomerService customerService;
    private final CustomerRegistrationDtoMapper customerRegistrationDtoMapper;
    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerNewCustomer(CustomerRegistrationDto customerRegistrationDto) {
        Customer customer = customerRegistrationDtoMapper.dtoToEntity(customerRegistrationDto);
        customer.setPaymentMethod(PaymentMethod.CASH_TO_THE_COURIER);
        customerService.createNewCustomer(customer);
        if (!userDetailsManager.userExists(customer.getEmail())) {
            String encodedPassword = passwordEncoder.encode(customer.getPassword());
            userDetailsManager.createUser(User.withUsername(customer.getEmail())
                    .password(encodedPassword)
                    .roles(Roles.USER.toString())
                    .build());
        }

    }

}
