package com.example.pizza.security;

import com.example.pizza.enam.Roles;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class AdminConfig {
    private final JdbcUserDetailsManager userDetailsManager;//хранение в баззе данных
    private final PasswordEncoder passwordEncoder; // кодировка пароля
    @Value("${admin.username}")
    private String adminUserName;
    @Value("${admin.password}")
    private String adminPassword;

    @PostConstruct
    public void createAdmin() {
        if (!userDetailsManager.userExists(adminUserName)) {
            String encodedPassword = passwordEncoder.encode(adminPassword);
            userDetailsManager.createUser(User.withUsername(adminUserName)
                    .password(encodedPassword)
                    .roles(Roles.ADMIN.toString(), Roles.USER.toString())
                    .build());
        }
    }
}
