package com.launchcode.parks_for_tails.services;

import com.launchcode.parks_for_tails.models.ParkComment;
import com.launchcode.parks_for_tails.data.ParkCommentRepository;
import com.launchcode.parks_for_tails.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling ParkComment-related operations.
 * Acts as an intermediary between the controller and the database.
 */
@Service
public class ParkCommentService {

    @Autowired
    private ParkCommentRepository parkCommentRepository;

    @Autowired
    private com.launchcode.parks_for_tails.services.UserService userService;

    /**
     * Method to add a new comment to the database.
     *
     * @param comment The comment object to be added.
     * @param userId  The ID of the user associated with the comment.
     */
    public void addComment(ParkComment comment, int userId) {
        // Set the user for the comment
        User user = userService.getUserById(userId);
        comment.setUser(user);

        // Save the comment to the database
        parkCommentRepository.save(comment);
    }

    /**
     * Method to get all comments for a specific park.
     *
     * @param parkId The ID of the park for which comments are requested.
     * @return A list of comments associated with the specified park ID.
     */
    public List<ParkComment> getCommentsByPark(Long parkId) {
        return parkCommentRepository.findByParkId(parkId);
    }

    /**
     * Method to update an existing comment in the database.
     *
     * @param commentId      The ID of the comment to be updated.
     * @param updatedComment The updated comment data.
     * @throws IllegalArgumentException If the comment with the specified ID is not found.
     */
    public void updateComment(Long commentId, ParkComment updatedComment) {
        // Check if the comment with the specified ID exists
        Optional<ParkComment> existingCommentOptional = parkCommentRepository.findById(commentId);

        if (existingCommentOptional.isPresent()) {
            // If exists, update the comment with the new data
            ParkComment existingComment = existingCommentOptional.get();
            existingComment.setComment(updatedComment.getComment());
            // You can update other fields as needed
            parkCommentRepository.save(existingComment);
        } else {
            // If not found, throw an exception or handle the scenario accordingly
            throw new IllegalArgumentException("Comment with ID " + commentId + " not found");
        }
    }

    /**
     * Method to delete a comment from the database by its ID.
     *
     * @param commentId The ID of the comment to be deleted.
     * @throws IllegalArgumentException If the comment with the specified ID is not found.
     */
    public void deleteComment(Long commentId) {
        // Check if the comment with the specified ID exists
        if (parkCommentRepository.existsById(commentId)) {
            // If exists, delete the comment
            parkCommentRepository.deleteById(commentId);
        } else {
            // If not found, throw an exception or handle the scenario accordingly
            throw new IllegalArgumentException("Comment with ID " + commentId + " not found");
        }
    }

    /**
     * Method to get a specific comment from the database by its ID.
     *
     * @param commentId The ID of the comment to be retrieved.
     * @return The comment with the specified ID, or null if not found.
     */
    public ParkComment getCommentById(Long commentId) {
        // Retrieve and return the comment with the specified ID
        return parkCommentRepository.findById(commentId).orElse(null);
    }

    /**
     * Method to get all comments from the database.
     *
     * @return A list of all comments in the database.
     */
    public List<ParkComment> getAllComments() {
        // Retrieve and return all comments in the database
        return parkCommentRepository.findAll();
    }
}