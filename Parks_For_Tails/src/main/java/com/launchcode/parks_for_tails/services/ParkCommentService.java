/*
package com.launchcode.parks_for_tails.services;

import com.launchcode.parks_for_tails.models.ParkComment;
import com.launchcode.parks_for_tails.models.User;
import com.launchcode.parks_for_tails.data.ParkCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkCommentService {

    @Autowired
    private ParkCommentRepository parkCommentRepository;

    @Autowired
    private UserService userService;

    public void addComment(ParkComment comment, int userId) {
        User user = userService.getUserById(userId);
        comment.setUser(user);
        parkCommentRepository.save(comment);
    }

    public List<ParkComment> getCommentsByPark(int parkId) {
        return parkCommentRepository.findByParkId(parkId);
    }

    public void updateComment(int commentId, ParkComment updatedComment) {
        Optional<ParkComment> existingCommentOptional = parkCommentRepository.findById(commentId);

        if (existingCommentOptional.isPresent()) {
            ParkComment existingComment = existingCommentOptional.get();
            existingComment.setComment(updatedComment.getComment());
            parkCommentRepository.save(existingComment);
        } else {
            throw new IllegalArgumentException("Comment with ID " + commentId + " not found");
        }
    }

    public void deleteComment(int commentId) {
        if (parkCommentRepository.existsById(commentId)) {
            parkCommentRepository.deleteById(commentId);
        } else {
            throw new IllegalArgumentException("Comment with ID " + commentId + " not found");
        }
    }

    public ParkComment getCommentById(int commentId) {
        return parkCommentRepository.findById(commentId).orElse(null);
    }

    public List<ParkComment> getAllComments() {
        return parkCommentRepository.findAll();
    }
}
*/
