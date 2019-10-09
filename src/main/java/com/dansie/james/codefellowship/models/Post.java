package com.dansie.james.codefellowship.models;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String body;
    String createdAt;

    public Post(){}

    public Post(String body, String createdAt, ApplicationUser owner) {
        this.body = body;
        this.createdAt = createdAt;
        this.owner = owner;
    }

    @ManyToOne
    ApplicationUser owner;

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
