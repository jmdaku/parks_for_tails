package com.launchcode.parks_for_tails.services;

import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.data.UserRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.launchcode.parks_for_tails.SecurityConfig;

import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User registerUser(User user) {
        // Validate incoming data (e.g., check if required fields are present)
        // You can also add more complex validation logic here

        // Securely handle the password
        // New field to store the raw (unencoded) password temporarily
        String password = user.getPassword();
        String pwhash = bCryptPasswordEncoder.encode(password);
        user.setPassword(pwhash);

        // Save the user to the database
        return userRepository.save(user);
    }

    public Optional<User> getUserById(int userId) {
        return userRepository.findById(userId);
    }
}
