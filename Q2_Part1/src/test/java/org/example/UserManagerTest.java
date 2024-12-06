package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserManagerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserManager userManager;

    @Test
    void testRegisterUser_WhenUsernameExists() {
        String username = "existingUser";
        String password = "password123";
        when(userService.usernameExists(username)).thenReturn(true);

        boolean result = userManager.registerUser(username, password);
        assertFalse(result, "False");
        verify(userService).usernameExists(username);
        verify(userService, never()).saveUser(anyString(), anyString());
    }

    @Test
    void testRegisterUser_WhenUsernameDoesNotExist() {
        String username = "newUser";
        String password = "password123";
        when(userService.usernameExists(username)).thenReturn(false);
        when(userService.saveUser(username, password)).thenReturn(true);

        boolean result = userManager.registerUser(username, password);
        assertTrue(result, "True");
        verify(userService).usernameExists(username);
        verify(userService).saveUser(username, password);
    }

    @Test
    void testRegisterUser_SaveUserFails() {
        String username = "newUser";
        String password = "password123";
        when(userService.usernameExists(username)).thenReturn(false);
        when(userService.saveUser(username, password)).thenReturn(false);

        boolean result = userManager.registerUser(username, password);
        assertFalse(result, "RegisterUser should return false");
        verify(userService).usernameExists(username);
        verify(userService).saveUser(username, password);
    }
}
