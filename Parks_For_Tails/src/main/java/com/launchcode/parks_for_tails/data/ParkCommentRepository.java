package com.launchcode.parks_for_tails.data;

import com.launchcode.parks_for_tails.models.ParkComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This interface extends JpaRepository, providing CRUD functionality for ParkComment entities
public interface ParkCommentRepository extends JpaRepository<ParkComment, Long> {

    // Custom query method to find comments by parkId
    // This method is automatically implemented by Spring Data JPA based on its name
    // It returns a list of ParkComment entities associated with the specified parkId
    List<ParkComment> findByParkId(Long parkId);

    // more query methods can be added here based on needs

    // JpaRepository provides various CRUD methods such as save, findById, findAll, delete, etc.
    // These methods are available for use without explicit implementation.
    // Spring Data JPA interprets method names and generates corresponding SQL queries.
}
