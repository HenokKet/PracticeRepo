package learn.memories.domain;

import learn.memories.data.DataAccessException;
import learn.memories.data.UserRepository;
import learn.memories.models.User;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // --- READ Operations ---

    /**
     * Finds a User by their primary key ID.
     * The returned User object will contain the decrypted plaintext password
     * represented as a byte array, or the encrypted data if the repository
     * is configured to return it that way.
     */
    public User findUserById(int id) throws DataAccessException {
        return repository.findById(id);
    }

    /**
     * Finds a User by their unique username.
     * The returned User object will contain the decrypted plaintext password
     * represented as a byte array, or the encrypted data if the repository
     * is configured to return it that way.
     */
    public User findUserByUsername(String username) throws DataAccessException {
        return repository.findByUsername(username);
    }

    public List<User> findAllUsers() throws DataAccessException {
        return repository.findAll();
    }

    // --- CREATE Operation ---

    /**
     * Adds a new User. The password (byte array) in the User object is passed to
     * the repository, which uses SQL's AES_ENCRYPT to store the encrypted value.
     */
    public UserResult add(User user) throws DataAccessException {
        UserResult result = validate(user);

        // Additional Validators
        if (user.getUserId() > 0) {
            result.addErrorMessage("User ID should not be set for a new user.");
        }

        // Check for duplicate username
        if (result.isSuccess() && repository.findByUsername(user.getUserName()) != null) {
            result.addErrorMessage("Username '" + user.getUserName() + "' is already taken.");
        }

        if (result.isSuccess()) {
            // The repository will handle encrypting the password before insertion
            user = repository.add(user);
            result.setUser(user);
        }

        return result;
    }

    // --- UPDATE Operation ---

    /**
     * Updates an existing User. If the password field in the User object is set,
     * it will be re-encrypted and updated in the database.
     */
    public UserResult update(User user) throws DataAccessException {
        UserResult result = validate(user);

        // Additional Validation
        if (user.getUserId() <= 0) {
            result.addErrorMessage("User Id is Required for an update.");
        }

        // Check for duplicate username (if the username is changing)
        if (result.isSuccess()) {
            User existingUser = repository.findByUsername(user.getUserName());
            if (existingUser != null && existingUser.getUserId() != user.getUserId()) {
                result.addErrorMessage("Username '" + user.getUserName() + "' is already in use by another account.");
            }
        }

        if (result.isSuccess()) {
            // The repository will handle encrypting the password before updating
            if (repository.update(user)) {
                result.setUser(user);
            } else {
                result.addErrorMessage("User Id " + user.getUserId() + " was not found.");
            }
        }
        return result;
    }

    // --- DELETE Operation ---

    public UserResult deleteById(int userId) throws DataAccessException {
        UserResult result = new UserResult();
        User userToDelete = repository.findById(userId); // Get user before potential deletion

        if (!repository.deleteById(userId)) {
            result.addErrorMessage("User Id " + userId + " was not found.");
        } else if (userToDelete != null) {
            result.setUser(userToDelete);
        }

        return result;
    }

    // --- Private Validation Method ---

    private UserResult validate(User user) {
        UserResult userResult = new UserResult();

        if (user == null) {
            userResult.addErrorMessage("User cannot be null.");
            return userResult;
        }

        // 1. Validate userName
        if (user.getUserName() == null || user.getUserName().isBlank()) {
            userResult.addErrorMessage("Username is required.");
        }

        // 2. Validate password (This is now a byte array)
        if (user.getPassword() == null || user.getPassword().length == 0) {
            userResult.addErrorMessage("Password data is required.");
        }
        // NOTE: Standard password length checks should ideally occur at the controller/DTO layer
        // before converting the plaintext String to a byte array, as the length of the
        // byte array here is ambiguous (could be plaintext bytes or encrypted bytes).
        // Since we are now using a byte array, we only validate the existence and non-zero length.

        return userResult;
    }
}
