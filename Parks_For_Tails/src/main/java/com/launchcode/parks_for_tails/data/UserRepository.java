package com.launchcode.parks_for_tails.data;

import com.launchcode.parks_for_tails.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    // Add custom query methods if needed
    User findByUsername(String username);
    Optional<User> findByEmail(String email);

    // Example of a custom query using JPQL
    // @Query("SELECT u FROM User u WHERE u.email = :email")
    // User findByEmail(@Param("email") String email);
}