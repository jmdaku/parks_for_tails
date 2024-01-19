package com.launchcode.parks_for_tails.models;

// Park.java
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Park {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String location;
    private String description;

    private double latitude;
    private double longitude;
    private int establishedYear;
    private List<String> facilities;
    private boolean petFriendly;
    private String imageUrl;

    public Park() {
        // Default constructor
    }


    // Constructors, getters, setters

    public Park(int id, String name, String location, String description, double latitude, double longitude, int establishedYear, List<String> facilities, boolean petFriendly, String imageUrl) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.establishedYear = establishedYear;
        this.facilities = facilities;
        this.petFriendly = petFriendly;
        this.imageUrl = imageUrl;
    }


    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Park park)) return false;
        return Double.compare(latitude, park.latitude) == 0 && Double.compare(longitude, park.longitude) == 0 && establishedYear == park.establishedYear && petFriendly == park.petFriendly && Objects.equals(getName(), park.getName()) && Objects.equals(getLocation(), park.getLocation()) && Objects.equals(getDescription(), park.getDescription()) && Objects.equals(facilities, park.facilities) && Objects.equals(imageUrl, park.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLocation(), getDescription(), latitude, longitude, establishedYear, facilities, petFriendly, imageUrl);
    }

    @Override
    public String toString() {
        return "Park{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", establishedYear=" + establishedYear +
                ", facilities=" + facilities +
                ", petFriendly=" + petFriendly +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
