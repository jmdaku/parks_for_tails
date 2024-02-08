package com.launchcode.parks_for_tails.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    // Constructors, getters, setters:
    //constructor
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    //empty constructor
    public User() {}

    //setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() { return id; }

}