package com.launchcode.parks_for_tails.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddParkFormDTO {

    @NotNull(message = "Park name is required")
    @NotBlank(message = "Park name cannot be blank")
    @Size(min = 3, max = 30, message = "Park name must be 3-30 characters.")
    private String name;

    @NotNull(message = "Park address is required")
    @NotBlank(message = "Park address cannot be blank")
    @Size(min = 10, max = 40, message = "Park address must be 10-40 characters.")
    private String address;

    private int latitude;

    private int longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
