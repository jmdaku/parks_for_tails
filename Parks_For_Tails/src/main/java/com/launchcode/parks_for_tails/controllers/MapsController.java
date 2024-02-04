package com.launchcode.parks_for_tails.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MapsController {

    // URL for the Google Maps API
    private final String GOOGLE_MAPS_API_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";

    // Endpoint to search for parks
    @GetMapping("/searchParks")
    public String searchParks(@RequestParam String query, @RequestParam String apiKey) {
        // Construct the URL for the Google Maps API request
        String url = GOOGLE_MAPS_API_URL + "?query=" + query + "&key=" + apiKey;

        // Forward the request to the Google Maps API
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
