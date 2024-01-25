package com.launchcode.parks_for_tails.data;

import com.launchcode.parks_for_tails.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


//interface extends JpaRepository and defines methods for database operations related to the User entity.
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
