package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.data.ParkRepository;
import com.launchcode.parks_for_tails.models.Park;
import com.launchcode.parks_for_tails.models.dto.AddParkFormDTO;
import com.launchcode.parks_for_tails.services.ParkService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//This controller class handles HTTP requests related to user registration and profile retrieval

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/parks")
public class AddParkController {

    @Autowired
    private ParkService parkService;

    @PostMapping("/addpark")
    public ResponseEntity<Object> savePark(@RequestBody AddParkFormDTO parkForm) {
        try {
            Park newPark = parkService.addPark(parkForm);
            return new ResponseEntity<>(newPark, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/{parkId}")
    public ResponseEntity<Object> getParkById(@PathVariable String parkId) {
        if ("addpark".equals(parkId)) {
            // Handle park storage separately
            return new ResponseEntity<>(HttpStatus.OK); // or redirect to registration page
        } else {
            // Attempt to get the user by ID
            return parkService.getParkById(Integer.parseInt(parkId))
                    .map(park -> new ResponseEntity<>(park, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
    }

}