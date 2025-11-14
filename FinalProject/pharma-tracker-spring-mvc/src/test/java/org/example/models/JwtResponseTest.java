package org.example.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseTest {

    @Test
    void shouldCreateJwtResponseAndReturnAllFields() {
        String token = "jwt-token-123";
        String username = "test_user";
        String firstName = "Alex";
        String lastName = "Johnson";

        JwtResponse response = new JwtResponse(token, username, firstName, lastName);

        assertEquals(token, response.getJwt());
        assertEquals(username, response.getUsername());
        assertEquals(firstName, response.getFirstName());
        assertEquals(lastName, response.getLastName());
    }

    @Test
    void shouldHandleEmptyStrings() {
        JwtResponse response = new JwtResponse("", "", "", "");

        assertEquals("", response.getJwt());
        assertEquals("", response.getUsername());
        assertEquals("", response.getFirstName());
        assertEquals("", response.getLastName());
    }

    @Test
    void shouldHandleNullValues() {
        JwtResponse response = new JwtResponse(null, null, null, null);

        assertNull(response.getJwt());
        assertNull(response.getUsername());
        assertNull(response.getFirstName());
        assertNull(response.getLastName());
    }
}
