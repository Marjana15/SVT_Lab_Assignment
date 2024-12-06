package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LoginManagerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private LoginManager loginManager;

    @Test
    void testLogin_ValidCredentials() throws Exception {
        String username = "validUser";
        String password = "validPassword";
        when(authenticationService.authenticate(username, password)).thenReturn(true);

        boolean result = loginManager.login(username, password);
        assertTrue(result, "Login should return true for valid credentials");
        verify(authenticationService).authenticate(username, password);
    }

    @Test
    void testLogin_InvalidCredentials() throws Exception {
        String username = "invalidUser";
        String password = "invalidPassword";
        when(authenticationService.authenticate(username, password)).thenReturn(false);

        boolean result = loginManager.login(username, password);
        assertFalse(result, "Login should return false for invalid credentials");
        verify(authenticationService).authenticate(username, password);
    }

    @Test
    void testLogin_NullUsername() {
        String username = null;
        String password = "password";
        Exception exception = assertThrows(Exception.class, () -> loginManager.login(username, password));
        assertEquals("Username and password cannot be null", exception.getMessage());
        verify(authenticationService, never()).authenticate(anyString(), anyString());
    }

    @Test
    void testLogin_NullPassword() {
        String username = "username";
        String password = null;
        Exception exception = assertThrows(Exception.class, () -> loginManager.login(username, password));
        assertEquals("Username and password cannot be null", exception.getMessage());
        verify(authenticationService, never()).authenticate(anyString(), anyString());
    }
}
