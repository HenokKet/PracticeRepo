package dndapi.domain;

import dndapi.data.UserJdbcRepository;
import dndapi.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserJdbcRepository repository;

    // Dependency Injection: Spring automatically provides the UserJdbcRepository instance.
    public UserService(UserJdbcRepository repository) {
        this.repository = repository;
    }

    // --- CRUD Operations leveraging the Repository ---

    public List<User> findAll() {
        // NOTE: In a real-world application, you would typically sanitize the returned
        // list to ensure the decrypted password field is set to null before returning
        // it to prevent accidental exposure (e.g., through a REST API).
        return repository.findAll();
    }

    public User findById(int userId) {
        return repository.findById(userId);
    }

    public User add(User user) {
        // Add basic validation (e.g., check for null password) before adding
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return null; // Or throw a custom exception
        }
        // The repository handles the encryption before storage.
        return repository.add(user);
    }

    public boolean update(User user) {
        // Add basic validation before updating
        if (user.getUserId() <= 0 || user.getPassword() == null || user.getPassword().isBlank()) {
            return false; // Or throw a custom exception
        }
        // The repository handles the encryption before storage.
        return repository.update(user);
    }

    public boolean deleteById(int userId) {
        return repository.deleteById(userId);
    }

    // --- Business Logic: Login Function ---

    /**
     * Attempts to validate user credentials against the database.
     * * @param userName The username supplied by the user.
     * @param password The plaintext password supplied by the user.
     * @return The complete User object if credentials are valid, or null otherwise.
     */
    public User login(String userName, String password) {
        // 1. Retrieve all users from the database.
        //    (In a production environment, you would use a more efficient query like
        //     'findByUserName' to avoid loading all users, but we'll use findAll
        //     since no 'findByUserName' exists in the current repository.)
        List<User> allUsers = repository.findAll();

        // 2. Iterate and find a match for the username.
        for (User user : allUsers) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                // 3. Compare the provided plaintext password against the
                //    decrypted password retrieved by the repository.
                if (user.getPassword().equals(password)) {
                    // Credentials match!
                    return user;
                }
                // Username found, but password failed.
                return null;
            }
        }

        // 4. No user found with the given username.
        return null;
    }
}