package com.launchcode.parks_for_tails.services;

import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.models.dto.LoginFormDTO;
import com.launchcode.parks_for_tails.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        // Implementation for user registration
        // Validation logic can be added here

        // Securely handle the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Save the user to the database
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        // Implementation for getting user by ID
        return userRepository.findById(userId);
    }



    @Override
    public Optional<User> loginUser(LoginFormDTO loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}