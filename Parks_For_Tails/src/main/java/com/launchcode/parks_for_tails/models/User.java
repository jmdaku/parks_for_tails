package com.launchcode.parks_for_tails.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Objects;


@Entity
public class User {

    // Unique identifier for each user.
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 5, max = 20, message= "Username must be between 5 & 20 characters.")
    private String username;

    @Email(message = "Must be a valid email.")
    private String email;

    // Encrypted password hash of the user
    @NotNull
    private String pwhash;

    // New field to store the raw (unencoded) password temporarily
    @Transient
    private String rawPassword;


    // List of comments made by the user.
    // This establishes a one-to-many relationship with ParkComment entities.
    // The mappedBy attribute indicates the inverse side of the relationship (user field in ParkComment).
    // CascadeType.ALL ensures that any changes to a User entity cascade to its associated comments.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ParkComment> comments;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<ParkFavorite> favorites;
// need to connect comments to favorites

    // Default constructor required by JPA
    public User() {}

    //Constructor for creating a new user with a username and password
    public User(String username, String password) {
        this.username = username;
        this.rawPassword = password;
        this.pwhash = encoder.encode(rawPassword);
    }

    // Encoder instance for encrypting and verifying passwords
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    //Check if the given password matches the stored encrypted password
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwhash);
    }


    // Getters & Setters:

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
        // Encrypt the raw password and set it to the pwhash field
        this.pwhash = encoder.encode(rawPassword);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ParkComment> getComments() {
        return comments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (id == user.id) if (Objects.equals(username, user.username)) return true;
        return false;
    }

    // may only need to match id?

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}