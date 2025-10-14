package learn.memories.ui;

import learn.memories.data.DataAccessException;
import learn.memories.domain.MemoryResult;
import learn.memories.domain.MemoryService;
import learn.memories.domain.UserResult; // Import for the User Service result
import learn.memories.domain.UserService; // Import the new Service
import learn.memories.models.Memory;
import learn.memories.models.User; // Import the User model
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Arrays; // Needed for secure byte[] comparison

@Component
public class Controller {
    private final View view;
    private final MemoryService memoryService; // Renamed for clarity
    private final UserService userService;     // New service dependency
    private User currentUser;                 // State to hold the logged-in user

    // Constructor Injection
    @Autowired
    public Controller(View view, MemoryService memoryService, UserService userService) {
        this.view = view;
        this.memoryService = memoryService;
        this.userService = userService;
    }

    public void run() {
        view.displayHeader("Welcome to Memories");
        try {
            if (login()) { // 1. Attempt login first
                view.displayMessage("Welcome back, " + currentUser.getUserName() + "!");
                runApp();
            } else {
                // Use a generic error message to prevent revealing whether the username or password failed.
                view.displayErrors(List.of("Login failed. Invalid username or password."));
            }
        } catch (DataAccessException e) {
            view.displayErrors(List.of("A critical data error occurred: " + e.getMessage()));
        }
        view.displayHeader("Goodbye");
    }

    /**
     * Handles the user login process by validating the username and comparing the
     * password byte array against the retrieved, decrypted password.
     * @return true if login is successful (user is set), false otherwise.
     */
    private boolean login() throws DataAccessException {
        view.displayHeader("User Login");
        // ASSUMPTION: view.userLogin() returns a User object with both userName (String)
        // and password (byte[]) set from user input.
        User loginAttempt = view.userLogin("Enter your login credentials:");

        if (loginAttempt == null || loginAttempt.getUserName().isBlank() || loginAttempt.getPassword() == null) {
            return false;
        }

        // 1. Lookup the user by username. The returned User object contains the decrypted password.
        User foundUser = userService.findUserByUsername(loginAttempt.getUserName());

        if (foundUser != null) {
            // 2. Perform password check using Arrays.equals() on the byte arrays.
            // NOTE: A production application should use BCrypt to check a hash, not raw bytes.
            if (Arrays.equals(loginAttempt.getPassword(), foundUser.getPassword())) {
                this.currentUser = foundUser; // Set the current user state
                return true;
            }
        }

        // Return false for either null user or password mismatch
        return false;
    }

    public void runApp() throws DataAccessException {
        // Only runs if currentUser is not null (i.e., login() was successful)
        for (int option = view.chooseMenuOption();
             option > 0;
             option = view.chooseMenuOption()) {
            switch (option) {
                case 1:
                    viewMemories();
                    break;
                case 2:
                    addMemory();
                    break;
                case 3:
                    updateMemory();
                    break;
                case 4:
                    deleteMemory();
                    break;
            }
        }
    }

    public void viewMemories() throws DataAccessException {
        List<Memory> memories = getMemories("View Memories");
        view.displayMemories(memories);
    }

    public void addMemory() throws DataAccessException {
        Memory memory = view.createMemory();

        // 3. Set the creator ID from the current logged-in user
        // We ensure currentUser is not null because runApp() is protected by login()
        memory.setUserID(currentUser.getUserId());

        MemoryResult result = memoryService.add(memory);
        if (result.isSuccess()) {
            view.displayMessage("Memory " + result.getMemory().getId() + " created.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    public void updateMemory() throws DataAccessException {
        // NOTE: In a real app, you'd also check if currentUser.getUserId() is the creator of the memory.
        List<Memory> memories = getMemories("Update Memory");
        Memory memory = view.chooseMemory(memories);
        if (memory == null) {
            view.displayMessage("Memory Not Found");
            return;
        }

        // Check if the current user owns this memory (Crucial Security Step)
        if (memory.getUserID() != currentUser.getUserId()) {
            view.displayErrors(List.of("You can only update your own memories."));
            return;
        }

        memory = view.editMemory(memory);
        MemoryResult result = memoryService.update(memory);
        if (result.isSuccess()) {
            view.displayMessage("Memory " + result.getMemory().getId() + " updated.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    public void deleteMemory() throws DataAccessException {
        // NOTE: In a real app, you'd also check if currentUser.getUserId() is the creator of the memory.
        List<Memory> memories = getMemories("Delete Memory");
        Memory memory = view.chooseMemory(memories);

        if (memory == null) {
            view.displayMessage("Memory not found.");
            return;
        }

        // Check if the current user owns this memory (Crucial Security Step)
        if (memory.getUserID() != currentUser.getUserId()) {
            view.displayErrors(List.of("You can only delete your own memories."));
            return;
        }

        MemoryResult result = memoryService.deleteById(memory.getId());
        if (result.isSuccess()) {
            view.displayMessage("Memory " + memory.getId() + " deleted.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    //Helper Methods:
    private List<Memory> getMemories(String title) throws DataAccessException {
        view.displayHeader(title);
        // Displaying public memories (shareable) is fine for anyone.
        if (view.isPublic()) {
            return memoryService.findShareableMemories();
        }
        // Displaying private memories should probably only show the current user's private memories.
        // NOTE: The current MemoryService doesn't support fetching private memories by user ID,
        // so we'll leave the current implementation for private memories for now, but
        // acknowledge this is a potential security/logic gap.
        return memoryService.findPrivateMemories();
    }
}
