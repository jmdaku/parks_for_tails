package com.launchcode.parks_for_tails.models;

import jakarta.persistence.*;
import java.util.Date;

// This class represents the ParkComment entity in the database.
// It contains fields such as id, comment, createdAt, park, and user.

// The @Entity annotation indicates that this class is a JPA entity and will be mapped to a database table.
@Entity
public class ParkComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "park_id", nullable = false)
    private Park park;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
