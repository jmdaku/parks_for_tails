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
    private String address;
    private int latitude;
    private int longitude;
//    private String location;
//    private String description;
//    private int establishedYear;
//    private List<String> facilities;
//    private boolean petFriendly;
//    private String imageUrl;

    public Park() {
        // Default constructor
    }


    // Constructors, getters, setters

//    public Park(int id, String name, String location, String description, double latitude, double longitude, int establishedYear, List<String> facilities, boolean petFriendly, String imageUrl) {
//        this.id = id;
//        this.name = name;
//        this.location = location;
//        this.description = description;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.establishedYear = establishedYear;
//        this.facilities = facilities;
//        this.petFriendly = petFriendly;
//        this.imageUrl = imageUrl;
//    }

    //simplified constructor for form use
public Park(int id, String name, String address, int latitude, int longitude) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;

}

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getLatitude() { return latitude; }

    public void setLatitude(int latitude) { this.latitude = latitude; }

    public int getLongitude() { return longitude; }

    public void setLongitude(int longitude) { this.longitude = longitude; }

    //    public String getLocation() { return location; }
//
//    public void setLocation(String location) { this.location = location; }
//
//    public String getDescription() { return description; }
//
//    public void setDescription(String description) { this.description = description; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Park park)) return false;
        return Integer.compare(latitude, park.latitude) == 0 && Integer.compare(longitude, park.longitude) == 0 && Objects.equals(getName(), park.getName());
    }


    //simplified for form use
    @Override
    public String toString() {
        return "Park{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    //simplified for form use
    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, latitude, longitude);
    }

    //    @Override
//    public int hashCode() {
//        return Objects.hash(getName(), getLocation(), getDescription(), latitude, longitude, establishedYear, facilities, petFriendly, imageUrl);
//    }

//    @Override
//    public String toString() {
//        return "Park{" +
//                "name='" + name + '\'' +
//                ", location='" + location + '\'' +
//                ", description='" + description + '\'' +
//                ", latitude=" + latitude +
//                ", longitude=" + longitude +
//                ", establishedYear=" + establishedYear +
//                ", facilities=" + facilities +
//                ", petFriendly=" + petFriendly +
//                ", imageUrl='" + imageUrl + '\'' +
//                '}';
//    }
}
