package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.data.UserRepository;
import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.models.dto.RegistrationFormDTO;
import com.launchcode.parks_for_tails.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;
import org.springframework.validation.Errors;


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
    public String displayRegistrationForm(Model model, HttpSession session) {
        // Add an empty RegistrationFormDTO to the model for form binding
        model.addAttribute(new RegistrationFormDTO());
        // Add a flag indicating whether a user is logged in to the model
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Validated RegistrationFormDTO registrationFormDTO,
                                          Errors errors,
                                          HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "register";
        }

        // Check if a user with the same username already exists
        User existingUser = userRepository.findByUsername(registrationFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyExists", "That username already exists");
            return "register";
        }

        //checking if password and verify password fields match
        String password = registrationFormDTO.getPassword();
        String verifyPassword = registrationFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            return "register";
        }

        //saves user & password; logs in & sends to search page
        User newUser = new User(registrationFormDTO.getUsername(), registrationFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        return "redirect:/search";
    }


    //login page
    @GetMapping("/login")
    public String displayLoginForm(Model model, HttpSession session) {
        // Add an empty LoginFormDTO to the model for form binding
        model.addAttribute(new LoginFormDTO());
        // Add a flag indicating whether a user is logged in to the model
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Validated LoginFormDTO loginFormDTO,
                                   Errors errors,
                                   HttpServletRequest request) {

        if (errors.hasErrors()) {
            return "login";
        }

        // Find the user in the database by username
        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        //retrieve & save password from login form
        String password = loginFormDTO.getPassword();

        //verify if username exists and username + password (hashed) match
        if (theUser == null || !theUser.isMatchingPassword(password)) {
            errors.rejectValue(
                    "password",
                    "login.invalid",
                    "Invalid login. Please try again."
            );
            return "login";
        }

        //if correct, login & send to search page
        setUserInSession(request.getSession(), theUser);
        return "redirect:/search";
    }

    //logout page
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";

    }
}