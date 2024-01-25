package com.launchcode.parks_for_tails.services;

import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.models.dto.LoginFormDTO;

import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> getUserById(Long userId);

    Optional<User> loginUser(LoginFormDTO loginForm);

}

