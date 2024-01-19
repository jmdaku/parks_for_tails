package com.launchcode.parks_for_tails.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingController {

    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}