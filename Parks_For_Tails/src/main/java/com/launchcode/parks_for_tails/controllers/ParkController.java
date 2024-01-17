package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.services.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// The @RestController annotation marks this class as a controller in a RESTful web service
@RestController
// @RequestMapping at the class level specifies that all handler methods in this controller are relative to the "/parks" path
@RequestMapping("/parks")
public class ParkController {

    // @Autowired is used for automatic dependency injection of the GoogleMapsService into this controller
    @Autowired
    private GoogleMapsService googleMapsService;

    // @GetMapping maps HTTP GET requests onto the searchParks method
    // @RequestParam indicates that a parameter named 'query' will be expected in the request
    @GetMapping("/search")
    public ResponseEntity<?> searchParks(@RequestParam String query) {
        try {
            // Invoke the GoogleMapsService to search for parks based on the query
            return ResponseEntity.ok(googleMapsService.searchParks(query));
        } catch (Exception ex) {
            // Basic exception handling, returning an internal server error response
            return ResponseEntity.internalServerError().body("An error occurred: " + ex.getMessage());
        }
    }

    // basic exception handler method
    // This method will handle all exceptions that are not explicitly caught in the controller's handler methods
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception ex) {
        // Here can log the exception to perform additional error handling as needed.
        // Returning a ResponseEntity with an error message and the HTTP status code INTERNAL_SERVER_ERROR
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }
}
