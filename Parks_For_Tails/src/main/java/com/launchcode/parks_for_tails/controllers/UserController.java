package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//This controller class handles HTTP requests related to user registration and profile retrieval


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        try {
            // Attempt to register the user
            User registeredUser = userService.registerUser(user);

            // Return the registered user or any other response you want
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle registration failure (e.g., duplicate email)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        if ("register".equals(userId)) {
            // Handle registration separately
            return new ResponseEntity<>(HttpStatus.OK); // or redirect to registration page
        } else {
            // Attempt to get the user by ID
            return userService.getUserById(Long.parseLong(userId))
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }
}

















