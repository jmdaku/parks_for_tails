package com.launchcode.parks_for_tails.models;

import jakarta.persistence.*;
import java.util.Date;

// This class represents the ParkComment entity in the database.
// It contains fields such as id, comment, createdAt, park, and user.

// The @Entity annotation indicates that this class is a JPA entity and will be mapped to a database table.
@Entity
public class ParkComment {

    // The @Id and @GeneratedValue annotations specify that the 'id' field is the primary key and will be automatically
    // generated using the GenerationType.IDENTITY strategy.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The @Column annotation is used to customize the column mapping in the database.
    @Column(nullable = false)
    private String comment;

    // 'comment' and 'createdAt' are non-nullable columns.
    // The @Temporal annotation specifies the temporal type of the 'createdAt' field (TIMESTAMP).
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    // The @ManyToOne annotation indicates a many-to-one relationship with the Park entity.
    // It specifies that the 'park' field represents the park associated with this comment.
    // The @JoinColumn annotation is used to specify the foreign key column name ('park_id').
    @ManyToOne
    @JoinColumn(name = "park_id", nullable = false)
    private Park park;

    // Optional: Add a reference to the user who made the comment
    // The @ManyToOne annotation indicates a many-to-one relationship with the User entity.
    // It specifies that the 'user' field represents the user associated with this comment.
    // The @JoinColumn annotation is used to specify the foreign key column name ('user_id').
    // This assumes you have a User entity representing application users.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructors, getters, and setters methods for initializing, accessing, and modifying the fields of the entity.

    // Getter and setter for the 'comment' field
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // Getter and setter for the 'id' field
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and setter for the 'createdAt' field
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Getter and setter for the 'park' field
    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    // Optional: Getter and setter for the 'user' field
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
