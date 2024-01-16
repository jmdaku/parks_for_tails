package com.launchcode.parks_for_tails.controllers;

import com.launchcode.parks_for_tails.models.ParkComment;
import com.launchcode.parks_for_tails.services.ParkCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")  // Base URL for comment-related endpoints
public class ParkCommentController {

    @Autowired
    private ParkCommentService parkCommentService;

    // Endpoint to add a new comment to a park
    @PostMapping("/add")
    public void addComment(@RequestBody ParkComment comment, @RequestParam int userId) {
        parkCommentService.addComment(comment, userId);
    }

    // Endpoint to get all comments for a specific park
    @GetMapping("/{parkId}")
    public List<ParkComment> getCommentsByPark(@PathVariable Long parkId) {
        return parkCommentService.getCommentsByPark(parkId);
    }

    // Additional endpoint to update an existing comment
    @PutMapping("/update/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody ParkComment updatedComment) {
        parkCommentService.updateComment(commentId, updatedComment);
    }

    // Additional endpoint to delete a comment by its ID
    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        parkCommentService.deleteComment(commentId);
    }

    // Additional endpoint to get a specific comment by its ID
    @GetMapping("/get/{commentId}")
    public ParkComment getCommentById(@PathVariable Long commentId) {
        return parkCommentService.getCommentById(commentId);
    }

    // Additional endpoint to get all comments (without specifying a park)
    @GetMapping("/all")
    public List<ParkComment> getAllComments() {
        return parkCommentService.getAllComments();
    }
}