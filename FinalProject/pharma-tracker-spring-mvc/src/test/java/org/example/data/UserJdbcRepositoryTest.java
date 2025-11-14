package org.example.data;

import org.example.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the UserJdbcRepository class.
 * Assumes an initial SQL script loads two sample users into the 'user' table:
 * 1: test_user_1 (user1@email.com, admin, password123)
 * 2: test_admin_1 (admin@email.com, user, securepass)
 */
@SpringBootTest
@Transactional
class UserJdbcRepositoryTest {

    @Autowired
    UserJdbcRepository repository;

    // Helper method to create a User object for comparison
    private User buildTestUser(int id,
                               String userName,
                               String email,
                               String firstName,
                               String lastName,
                               String password) {

        User user = new User();
        user.setUserId(id);
        user.setUserName(userName);
        user.setUserEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        return user;
    }

    // --- READ Tests ---

    @Test
    void shouldFindAllUsers() {
        List<User> users = repository.findAll();

        assertNotNull(users);
        // Assumes initial SQL script leaves 2 users in medUser
        assertEquals(3, users.size());
    }

    @Test
    void shouldFindByIdOne() {
        // Adjusted to match medUser schema and expected seed data
        User expected = buildTestUser(
                1,
                "test_user_1",
                "user1@email.com",
                "Alex",
                "Johnson",
                "password123"
        );

        User actual = repository.findById(1);

        assertNotNull(actual);
        // Check core fields and confirm the plaintext password was correctly decrypted
        assertEquals(expected.getUserName(), actual.getUserName());
        assertEquals(expected.getUserEmail(), actual.getUserEmail());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindMissingId() {
        User actual = repository.findById(999);
        assertNull(actual);
    }

    // --- CREATE Test ---

    @Test
    void shouldAddUser() {
        User user = new User();
        user.setUserName("new_user");
        user.setUserEmail("new@email.com");
        user.setFirstName("New");
        user.setLastName("User");
        user.setPassword("temp_password"); // Plaintext to be encrypted

        User actual = repository.add(user);

        assertNotNull(actual);
        // User ID should be generated and greater than 0
        assertTrue(actual.getUserId() > 0);

        // Verify it can be retrieved, and the password is correctly decrypted
        User retrieved = repository.findById(actual.getUserId());
        assertEquals(actual.getUserEmail(), retrieved.getUserEmail());
        assertEquals(actual.getUserName(), retrieved.getUserName());
        assertEquals(actual.getFirstName(), retrieved.getFirstName());
        assertEquals(actual.getLastName(), retrieved.getLastName());
        assertEquals("temp_password", retrieved.getPassword()); // Check decrypted password
        assertEquals(actual, retrieved);
    }

    // --- UPDATE Tests ---

    @Test
    void shouldUpdateExistingUser() {
        // Retrieve an existing user to modify
        User userToUpdate = repository.findById(2);
        assertNotNull(userToUpdate);

        userToUpdate.setUserName("updated_admin");
        userToUpdate.setFirstName("Updated");
        userToUpdate.setLastName("Admin");
        userToUpdate.setPassword("new_secure_pass"); // Update the password

        assertTrue(repository.update(userToUpdate));

        // Verify the change in the database
        User updatedUser = repository.findById(2);
        assertNotNull(updatedUser);
        assertEquals("updated_admin", updatedUser.getUserName());
        assertEquals("Updated", updatedUser.getFirstName());
        assertEquals("Admin", updatedUser.getLastName());
        assertEquals("new_secure_pass", updatedUser.getPassword()); // Verify decrypted new password
    }

    @Test
    void shouldNotUpdateMissingUser() {
        User user = new User();
        user.setUserId(20000); // ID that doesn't exist
        user.setUserName("Missing");
        user.setUserEmail("m@e.com");
        user.setFirstName("Missing");
        user.setLastName("User");
        user.setPassword("fail");

        assertFalse(repository.update(user));
    }

    // --- DELETE Tests ---

    @Test
    void shouldDeleteExistingUser() {
        // ID 1 should exist
        assertTrue(repository.deleteById(1));

        // Verify it is gone
        assertNull(repository.findById(1));

        // Ensure the list size decreases
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldNotDeleteMissingUser() {
        assertFalse(repository.deleteById(40000));
    }
}