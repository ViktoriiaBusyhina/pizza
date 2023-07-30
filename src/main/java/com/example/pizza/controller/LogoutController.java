package com.example.pizza.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controller class for handling user logout.
 */
@RestController
public class LogoutController {

    /**
     * Endpoint to handle user logout.
     * Perform the logout logic here based on your application's authentication mechanism.
     * For example, clear the user session, invalidate tokens, or perform other necessary actions.
     */
    @PostMapping("logout")
    public void logout() {
        // Implement your logout logic here
    }
}
