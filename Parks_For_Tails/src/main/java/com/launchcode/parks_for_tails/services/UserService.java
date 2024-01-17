package com.launchcode.parks_for_tails.services;

import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

/*
*
     * Get a user by their ID.
     *
     * @param userId The ID of the user.
     * @return The user with the specified ID, or null if not found.
*/


    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

/**
     * Get a user by their username.
     *
     * @param username The username of the user.
     * @return The user with the specified username, or null if not found.*/


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

/*
*
     * Get all users in the system.
     *
     * @return A list of all users.
*/


    public List<User> getAllUsers() {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> usersList = new ArrayList<>();
        usersIterable.forEach(usersList::add);
        return usersList;
    }

/**
     * Save or update a user in the database.
     *
     * @param user The user object to be saved or updated.*/


    public void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }

/*
*
     * Delete a user by their ID.
     *
     * @param userId The ID of the user to be deleted.
*/


    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    // Add other methods as needed...
}
