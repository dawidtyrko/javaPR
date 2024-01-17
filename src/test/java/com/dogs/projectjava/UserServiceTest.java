package com.dogs.projectjava;

import com.dogs.projectjava.entity.User;
import com.dogs.projectjava.repo.UserRepository;
import com.dogs.projectjava.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testDisableByUsername() {
        String username = "user1";
        User mockUser = new User(username, "password", 1);

        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        userService.disableByUsername(username);

        assertEquals(0, mockUser.getEnabled());
        verify(userRepository, times(1)).save(mockUser);
    }
    @Test
    void testDisableByUsernameNotFound() {
        String username = "nonexistentUser";

        when(userRepository.findByUsername(username)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> userService.disableByUsername(username));

        verify(userRepository, never()).save(any());
    }

    @Test
    void testEnableByUsername() {
        String username = "user1";
        User mockUser = new User(username, "password", 0);

        when(userRepository.findByUsername(username)).thenReturn(mockUser);

        userService.enableByUsername(username);

        assertEquals(1, mockUser.getEnabled());
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    void testEnableByUsernameNotFound() {
        String username = "nonexistentUser";

        when(userRepository.findByUsername(username)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> userService.enableByUsername(username));

        verify(userRepository, never()).save(any());
    }
}
