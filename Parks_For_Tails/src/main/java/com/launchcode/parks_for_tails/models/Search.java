package com.launchcode.parks_for_tails.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int searchId;
    private static int nextId = 1;
    private String query;
    private double latitude;
    private double longitude;
    private boolean petFriendly;

    public Search() {
    }



    public Search(String query, double latitude, double longitude, boolean petFriendly,int searchId ) {
        this.query = query;
        this.latitude = latitude;
        this.longitude = longitude;
        this.petFriendly = petFriendly;
        this.searchId = nextId;
        nextId++;
    }

    public int getSearchId() {
        return searchId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

}
