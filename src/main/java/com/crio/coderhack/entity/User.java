package com.crio.coderhack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

@Document(collection = "users")
public class User {
    @Id
    private String userId;
    private String username;
    private int score;
    private Set<String> badges = new HashSet<>();
    private Set<Integer> scoreList;

    // Default constructor
    public User() {
    }

    // Constructor with parameters
    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.score = 0; // Initial score is 0
        this.badges = new HashSet<>();
        this.scoreList.add(0);// Initial badges are empty
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        scoreList.add(score);
        this.score = Collections.max(scoreList);
    }

    public Set<String> getBadges() {
        return badges;
    }

    public void setBadges(Set<String> badges) {
        this.badges = badges;
    }

    // Override toString() method for better readability
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", badges=" + badges +
                '}';
    }
}
