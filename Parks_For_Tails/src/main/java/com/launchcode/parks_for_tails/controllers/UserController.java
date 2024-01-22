package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.data.UserRepository;
import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.models.dto.RegistrationFormDTO;
import com.launchcode.parks_for_tails.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launchcode.parks_for_tails.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
//@Controller
//public class UserController {
//
//    @Autowired
//    static UserRepository userRepository;
//
//    // user ID key
//    private static final String userSessionKey = "user";
//
//    //stores user session
//    private static void setUserInSession(HttpSession session, User user) {
//        session.setAttribute(userSessionKey, user.getId());
//    }
//
//    private <T> String convertMapToJson(ResponseEntity<T> ok) {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Map<String, String> elements = new HashMap();
//        elements.put("Key1", "Value1");
//        elements.put("Key2", "Value2");
//        elements.put("Key3", "Value3");
//
//
//        String json = null;
//        try {
//            json = objectMapper.writeValueAsString(elements);
//            System.out.println(json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        return json;
//
//    }
//
//
//    //get user if session exists (not null or empty)
//    public static User getUserFromSession(HttpSession session) {
//
//        // Retrieve the user ID from the session
//        Integer userId = (Integer) session.getAttribute(userSessionKey);
//
//        // Check if the session contains a user ID
//        if (userId == null) {
//            return null;
//        }
//
//        // Attempt to find the user in the database by ID
//        Optional<User> userOpt = userRepository.findById(userId);
//
//        // Check if the user with the given ID exists
//        if (userOpt.isEmpty()) {
//            return null;
//        }
//
//        // Return the user if found
//        return userOpt.get();
//    }
//
//
//    //registration form
//    @GetMapping("/register")
//    public ResponseEntity<RegistrationFormDTO> displayRegistrationForm() {
//        RegistrationFormDTO registrationFormDTO = new RegistrationFormDTO();
//        return ResponseEntity.ok(registrationFormDTO);
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@RequestBody RegistrationFormDTO registrationFormDTO,
//                               Errors errors,
//                               HttpServletRequest request) {
//
//        User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());
//
//        if (errors.hasErrors()) {
//            return convertMapToJson(ResponseEntity.ok(Map.of("success", false, "message", "Validation error")));
//        }
//
//        // Check if a user with the same username already exists
//        if (existingUser != null) {
//            errors.rejectValue("username", "username.alreadyExists", "That username already exists");
//            return convertMapToJson(ResponseEntity.ok(Map.of("success", false, "message", "Username already exists")));
//        }
//
//        // checking if password and verify password fields match
//        String password = registrationFormDTO.getPassword();
//        String verifyPassword = registrationFormDTO.getVerifyPassword();
//        if (!password.equals(verifyPassword)) {
//            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
//            return convertMapToJson(ResponseEntity.ok(Map.of("success", false, "message", "Passwords do not match")));
//        }
//
//        // saves user & password; logs in & sends to search page
//        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword());
//        userRepository.save(newUser);
//        // setUserInSession(request.getSession(), newUser);
//        return convertMapToJson(ResponseEntity.ok(Map.of("success", true, "message", "User successfully registered")));
//    }
//
//
//
//
//
//    //login page
//    @GetMapping("/login")
//    public ResponseEntity<LoginFormDTO> displayLoginForm() {
//        LoginFormDTO loginFormDTO = new LoginFormDTO();
//        return ResponseEntity.ok(loginFormDTO);
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<?> processLoginForm(@RequestBody @Validated LoginFormDTO loginFormDTO,
//                                   Errors errors) {
//
//        if (errors.hasErrors()) {
//            return ResponseEntity.ok(Map.of("success", false, "message", true));
//        }
//
//        // Find the user in the database by username
//        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());
//
//        //retrieve & save password from login form
//        String password = loginFormDTO.getPassword();
//
//        //verify if username exists and username + password (hashed) match
//        if (theUser == null || !theUser.isMatchingPassword(password)) {
//            return ResponseEntity.badRequest().body("Invalid login");
//        }
//
//        return ResponseEntity.ok(Map.of("success", true, "message", "User logged in successfully", "userId", theUser.getId()));
//
//        //if correct, login & send to search page
////        setUserInSession(request.getSession(), theUser);
////        return "redirect:/search";
//    }
//
//    //logout page
//    @GetMapping("/logout")
//    public ResponseEntity<?> logout(){
//        return ResponseEntity.ok("Logout successful");
//
//    }
//}

@RestController
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

//    @GetMapping("/{userId}")
//    public ResponseEntity<User> getUserById(@PathVariable String userId) {
//        if ("register".equals(userId)) {
//            // Handle registration separately
//            return new ResponseEntity<>(HttpStatus.OK); // or redirect to registration page
//        } else {
//            // Attempt to get the user by ID
//            return userService.getUserById(Long.parseLong(userId))
//                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
//                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        }
//    }
}
