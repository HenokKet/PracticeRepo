package org.example.domain;

import org.example.models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @Test
    void shouldGenerateNonNullToken() {
        JwtService service = new JwtService();
        User user = buildUser(1, "test_user");

        String token = service.generateToken(user);

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    void shouldIncludeUserInfoInToken() {
        JwtService service = new JwtService();
        User user = buildUser(42, "alice");

        String token = service.generateToken(user);

        // Expected format: "fake-jwt-<id>:<username>-<timestamp>"
        assertTrue(token.startsWith("fake-jwt-"));
        assertTrue(token.contains("42:alice"));
    }

    @Test
    void shouldGenerateDifferentTokensForSameUserAtDifferentTimes() throws InterruptedException {
        JwtService service = new JwtService();
        User user = buildUser(7, "bob");

        String token1 = service.generateToken(user);
        Thread.sleep(5); // tiny delay to ensure different timestamps
        String token2 = service.generateToken(user);

        assertNotEquals(token1, token2);
    }

    private User buildUser(int id, String username) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(username);
        return user;
    }
}
