package com.crio.coderhack.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.crio.coderhack.dto.UpdateScoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crio.coderhack.entity.User;
import com.crio.coderhack.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        // Validate userId format if necessary
        if (userId == null || userId.trim().isEmpty()) {
            return ResponseEntity.badRequest().build(); // or throw a custom exception
        }

        logger.info("Fetching user with ID: " + userId);

        Optional<User> userOpt = userService.getUserById(userId);

        return userOpt
            .map(user -> {
                logger.info("User found: " + user);
                return ResponseEntity.ok(user);
            })
            .orElseGet(() -> {
                logger.warning("User not found for ID: " + userId);
                return ResponseEntity.notFound().build();
            });
    }

    @PostMapping
    public User createUser(@RequestBody User user) {

        return userService.createUser(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId, @RequestBody UpdateScoreRequest request) {
        int score = request.getScore();
        if (score < 0 || score > 100) {
            return ResponseEntity.badRequest().build();
        }
        Optional<User> user = userService.updateUserScore(userId, score);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}