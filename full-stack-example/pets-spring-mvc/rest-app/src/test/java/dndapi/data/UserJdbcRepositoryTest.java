package dndapi.data;

import dndapi.models.User;
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
    private User buildTestUser(int id, String name, String email, String role, String password) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(name);
        user.setUserEmail(email);
        user.setUserRole(role);
        user.setPassword(password);
        return user;
    }

    // --- READ Tests ---

    @Test
    void shouldFindAllUsers() {
        List<User> users = repository.findAll();

        assertNotNull(users);
        // Assumes initial SQL script leaves 2 users
        assertEquals(2, users.size());
    }

    @Test
    void shouldFindByIdOne() {
        User expected = buildTestUser(1, "test_user_1", "user1@email.com", "admin", "password123");

        User actual = repository.findById(1);

        assertNotNull(actual);
        // Check core fields and confirm the plaintext password was correctly decrypted
        assertEquals(expected.getUserName(), actual.getUserName());
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
        user.setUserRole("user");
        user.setPassword("temp_password"); // Plaintext to be encrypted

        User actual = repository.add(user);

        assertNotNull(actual);
        // User ID should be generated and greater than 0
        assertTrue(actual.getUserId() > 0);

        // Verify it can be retrieved, and the password is correctly decrypted
        User retrieved = repository.findById(actual.getUserId());
        assertEquals(actual.getUserEmail(), retrieved.getUserEmail());
        assertEquals("temp_password", retrieved.getPassword()); // Check decrypted password
        assertEquals(actual, retrieved);
    }

    // --- UPDATE Tests ---

    @Test
    void shouldUpdateExistingUser() {
        // Retrieve an existing user to modify
        User userToUpdate = repository.findById(2);
        userToUpdate.setUserName("updated_admin");
        userToUpdate.setUserRole("super_admin");
        userToUpdate.setPassword("new_secure_pass"); // Update the password

        assertTrue(repository.update(userToUpdate));

        // Verify the change in the database
        User updatedUser = repository.findById(2);
        assertEquals("updated_admin", updatedUser.getUserName());
        assertEquals("super_admin", updatedUser.getUserRole());
        assertEquals("new_secure_pass", updatedUser.getPassword()); // Verify decrypted new password
    }

    @Test
    void shouldNotUpdateMissingUser() {
        User user = new User();
        user.setUserId(20000); // ID that doesn't exist
        user.setUserName("Missing");
        user.setUserEmail("m@e.com");
        user.setUserRole("none");
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
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldNotDeleteMissingUser() {
        assertFalse(repository.deleteById(40000));
    }
}