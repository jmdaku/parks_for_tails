package com.launchcode.parks_for_tails.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling registration-related requests.
 * The @Controller annotation marks this class as a Spring MVC controller.
 */
@Controller
public class RegisterController {

    @GetMapping("/signup")
    public String register() {
        return "register";
    }
}
