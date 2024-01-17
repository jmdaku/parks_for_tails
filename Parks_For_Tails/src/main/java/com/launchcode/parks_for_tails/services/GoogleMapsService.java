package com.launchcode.parks_for_tails.services;

// Importing necessary classes from the Google Maps API and Spring Framework
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// Importing IOException for handling input/output exceptions
import java.io.IOException;

// Service annotation indicates that this class is a Spring service component
@Service
public class GoogleMapsService {

    // Injecting the Google Maps API key from application.properties
    @Value("${google.maps.api-key}")
    private String apiKey;

    // Method to search for parks based on a query
    public PlacesSearchResult[] searchParks(String query) throws InterruptedException, ApiException, IOException {
        // Building the GeoApiContext with the provided API key
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        // Calling the private method to execute the text search query
        return callTextSearchQuery(context, query);
    }

    // Private method to execute a text search query using the Google Maps API
    private PlacesSearchResult[] callTextSearchQuery(GeoApiContext context, String query)
            throws InterruptedException, ApiException, IOException {
        // Executing the text search query and awaiting the response
        PlacesSearchResponse response = PlacesApi.textSearchQuery(context, query).await();
        // Returning the array of search results
        return response.results;
    }
}
