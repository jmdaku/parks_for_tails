/*
package com.launchcode.parks_for_tails.controllers;
import com.launchcode.parks_for_tails.models.User;

import com.launchcode.parks_for_tails.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        // Create a new user instance
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Use setRawPassword instead of setPassword

        try {
            // Save the user to the database
            userRepository.save(user);
            System.out.println("User saved successfully: " + user.getUsername());

            // Add these lines for debugging
            System.out.println("Redirecting to login page...");
        } catch (Exception e) {
            System.err.println("Error saving user: " + e.getMessage());
        }

        // Redirect to a success page or login page
        return "redirect:/login";
    }
}
*/
