package com.crio.coderhack.service;

import java.util.List;
import java.util.Optional;

import com.crio.coderhack.entity.User;

public interface IUserService {
    public List<User> getAllUsers();
    public Optional<User> getUserById(String userId);
    public User createUser(User user);
    public Optional<User> updateUserScore(String userId, int score);
    public void deleteUser(String userId);
}
