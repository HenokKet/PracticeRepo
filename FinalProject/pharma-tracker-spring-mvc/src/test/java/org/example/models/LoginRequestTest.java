package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void shouldSetAndGetUsername() {
        LoginRequest request = new LoginRequest();
        request.setUsername("test_user");

        assertEquals("test_user", request.getUsername());
    }

    @Test
    void shouldSetAndGetPassword() {
        LoginRequest request = new LoginRequest();
        request.setPassword("secret123");

        assertEquals("secret123", request.getPassword());
    }

    @Test
    void shouldSetAndGetUsernameAndPasswordTogether() {
        LoginRequest request = new LoginRequest();
        request.setUsername("combo_user");
        request.setPassword("combo_pass");

        assertEquals("combo_user", request.getUsername());
        assertEquals("combo_pass", request.getPassword());
    }
}
