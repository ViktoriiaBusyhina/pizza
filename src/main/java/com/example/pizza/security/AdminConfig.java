package com.example.pizza.security;

import com.example.pizza.enam.Roles;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
/**
 * Configuration class for setting up the admin user in the system.
 * This class creates the admin user during application startup if it doesn't already exist.
 */
@Configuration
@RequiredArgsConstructor
public class AdminConfig {
    /**
     * The user details manager for managing user information in the system.
     * This is used to check if the admin user already exists and to create it if needed.
     */
    private final JdbcUserDetailsManager userDetailsManager;

    /**
     * The password encoder used to encode the admin user's password before storing it in the database.
     */
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String adminUserName;

    @Value("${admin.password}")
    private String adminPassword;

    /**
     * Method to create the admin user during application startup if it doesn't already exist.
     * If the admin user doesn't exist, it is created with the specified username, encoded password,
     * and roles of "ADMIN" and "USER".
     */
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