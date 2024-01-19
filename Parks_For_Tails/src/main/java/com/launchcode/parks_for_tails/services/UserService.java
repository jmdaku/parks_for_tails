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

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> usersList = new ArrayList<>();
        usersIterable.forEach(usersList::add);
        return usersList;
    }

    public void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}