package com.crio.coderhack.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.coderhack.entity.User;
import com.crio.coderhack.repository.UserRepository;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByScoreDesc();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public User createUser(User user) {
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return userRepository.save(user);
    }

    public Optional<User> updateUserScore(String userId, int score) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            updateBadges(user);
            user.setScore(score);
            updateBadges(user);
            userRepository.save(user);
        }
        return userOpt;
    }

    private void updateBadges(User user) {
        if (user.getScore() >= 1 && user.getScore() < 30) {
            user.getBadges().add("Code Ninja");
        } else if (user.getScore() >= 30 && user.getScore() < 60) {
            user.getBadges().add("Code Champ");
        } else if (user.getScore() >= 60 && user.getScore() <= 100) {
            user.getBadges().add("Code Master");
        }
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}