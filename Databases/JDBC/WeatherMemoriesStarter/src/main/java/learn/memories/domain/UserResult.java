package learn.memories.domain;

import learn.memories.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the result of a user service operation, including any validation
 * errors and the resultant or affected User object. This uses the standard
 * result object pattern for returning domain layer validation results.
 */
public class UserResult {
    private final ArrayList<String> errorMessages = new ArrayList<>();
    private User user;

    /**
     * Checks if the operation was successful (i.e., no error messages were added).
     * @return true if successful, false otherwise.
     */
    public boolean isSuccess() {
        return errorMessages.isEmpty();
    }

    /**
     * Gets a list of all accumulated error messages.
     * @return a copy of the list of error messages.
     */
    public List<String> getErrorMessages() {
        // Return a copy to prevent external modification of the internal list
        return new ArrayList<>(errorMessages);
    }

    /**
     * Adds an error message to the result.
     * @param message the error message string.
     */
    public void addErrorMessage(String message) {
        errorMessages.add(message);
    }

    /**
     * Gets the User object associated with the result (e.g., the added, updated, or deleted user).
     * @return the User object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the User object associated with the result.
     * @param user the User object.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
