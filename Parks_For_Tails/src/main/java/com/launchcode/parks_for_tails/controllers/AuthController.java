package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.models.dto.LoginFormDTO;
import com.launchcode.parks_for_tails.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; // Add this import
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginFormDTO loginForm) {
        try {
            // Attempt to log in the user
            User loggedInUser = userService.loginUser(loginForm)
                    .orElseThrow(() -> new RuntimeException("Login failed. Please check your credentials."));

            // Return the logged-in user
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } catch (Exception e) {
            // Handle login failure
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}