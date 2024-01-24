package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.data.UserRepository;
import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.models.dto.LoginFormDTO;
import com.launchcode.parks_for_tails.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//This controller class handles HTTP requests related to user registration and profile retrieval

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    static UserRepository userRepository;

    // user ID key
    private static final String userSessionKey = "user";

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


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //registration form
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        try {
            // Attempt to register the user
            User registeredUser = userService.registerUser(user);

            // Return the registered user or any other response you want
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
            // saves user & password; logs in & sends to search page

        } catch (Exception e) {
            // Handle registration failure (e.g., duplicate email)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable String userId) {
        if ("register".equals(userId)) {
            // Handle registration separately
            return new ResponseEntity<>(HttpStatus.OK); // or redirect to registration page
        } else {
            // Attempt to get the user by ID
            return userService.getUserById((int) Long.parseLong(userId))
                    .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

    //login page
    @GetMapping("/login")
    public ResponseEntity<LoginFormDTO> displayLoginForm() {
        LoginFormDTO loginFormDTO = new LoginFormDTO();
        return ResponseEntity.ok(loginFormDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> processLoginForm(@RequestBody LoginFormDTO loginFormDTO, HttpSession session) {

         try {
            // Attempt to log in the user
            Optional<User> loggedInUser = userService.loginUser(loginFormDTO);

            // Check if login was successful
            if (loggedInUser.isPresent()) {
                // Store the user in the session (you might want to use a more secure method)
                session.setAttribute("loggedInUser", loggedInUser.get());

                // Redirect to the /profile page
                return new ResponseEntity<>("Redirecting to /profile", HttpStatus.FOUND);
            } else {
                // Handle invalid login credentials
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            // Handle log in failure
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

















