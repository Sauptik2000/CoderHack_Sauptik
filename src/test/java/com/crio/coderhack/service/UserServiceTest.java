package com.crio.coderhack.service;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.crio.coderhack.entity.User;
import com.crio.coderhack.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "testuser");
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAllByOrderByHighestScoreDesc()).thenReturn(Arrays.asList(user));
        assertEquals(1, userService.getAllUsers().size());
        verify(userRepository, times(1)).findAllByOrderByHighestScoreDesc();
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        assertTrue(userService.getUserById("1").isPresent());
        verify(userRepository, times(1)).findById("1");
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(new User("2", "newuser"));
        assertEquals(0, createdUser.getScore());
        assertTrue(createdUser.getBadges().isEmpty());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUserScore() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        userService.updateUserScore("1", 50);
        assertEquals(50, user.getScore());
        assertTrue(user.getBadges().contains("Code Champ"));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById("1");
        userService.deleteUser("1");
        verify(userRepository, times(1)).deleteById("1");
    }
}