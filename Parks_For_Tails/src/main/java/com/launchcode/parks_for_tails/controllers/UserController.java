package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.data.UserRepository;
import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.models.dto.RegistrationFormDTO;
import com.launchcode.parks_for_tails.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;
import org.springframework.validation.Errors;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) //5186
@Controller
public class UserController {

    @Autowired
    static UserRepository userRepository;

    private static final String userSessionKey = "user";  // user ID key

    //stores user session
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    //get user if session exists (not null or empty)
    public static User getUserFromSession(HttpSession session) {

        // Retrieve the user ID from the session
        Integer userId = (Integer) session.getAttribute(userSessionKey);

        // Check if the session contains a user ID
        if (userId == null) {
            return null;
        }

        // Attempt to find the user in the database by ID
        Optional<User> userOpt = userRepository.findById(userId);

        // Check if the user with the given ID exists
        if (userOpt.isEmpty()) {
            return null;
        }

        // Return the user if found
        return userOpt.get();
    }


    //registration form
    @GetMapping("/register")
    public ResponseEntity<RegistrationFormDTO> displayRegistrationForm() {
        RegistrationFormDTO registrationFormDTO = new RegistrationFormDTO();
        return ResponseEntity.ok(registrationFormDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationFormDTO registrationFormDTO, Errors errors,
                                          HttpServletRequest request) {

        User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());

        if (errors.hasErrors()) {
            return ResponseEntity.ok(Map.of("success", false, "message", true));
        }

        // Check if a user with the same username already exists
        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyExists", "That username already exists");
            //return "register";
            return ResponseEntity.ok(Map.of("success", false, "message", true));
        }

        //checking if password and verify password fields match
        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return ResponseEntity.ok(Map.of("success", false, "message", "Passwords do not match"));
        }

        //saves user & password; logs in & sends to search page
        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        return ResponseEntity.ok(Map.of("success", true, "message", "User successfully registered"));
    }


    //login page
    @GetMapping("/login")
    public ResponseEntity<LoginFormDTO> displayLoginForm() {
        LoginFormDTO loginFormDTO = new LoginFormDTO();
        return ResponseEntity.ok(loginFormDTO);
    }



    @PostMapping("/login")
    public ResponseEntity<?> processLoginForm(@RequestBody @Validated LoginFormDTO loginFormDTO,
                                   Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.ok(Map.of("success", false, "message", true));
        }

        // Find the user in the database by username
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        //retrieve & save password from login form
        String password = loginFormDTO.getPassword();

        //verify if username exists and username + password (hashed) match
        if (theUser == null || !theUser.isMatchingPassword(password)) {
            return ResponseEntity.badRequest().body("Invalid login");
        }

        return ResponseEntity.ok(Map.of("success", true, "message", "User logged in successfully", "userId", theUser.getId()));

        //if correct, login & send to search page
//        setUserInSession(request.getSession(), theUser);
//        return "redirect:/search";
    }

    //logout page
    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok("Logout successful");

    }
}

