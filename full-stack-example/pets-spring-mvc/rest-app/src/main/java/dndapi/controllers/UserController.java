package dndapi.controllers;

import dndapi.models.LoginRequest;
import dndapi.models.JwtResponse;
import dndapi.models.User;
import dndapi.service.JwtService;
import dndapi.domain.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService; // New dependency for token generation

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    // --- 1. Login Function ---
    // Maps to POST /api/user/login
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        // Attempt to validate credentials using the UserService
        User user = userService.login(request.getUsername(), request.getPassword());

        if (user == null) {
            // Credentials failed
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED); // 401
        }

        // Credentials validated: Generate JWT
        String token = jwtService.generateToken(user);

        // Return the token, username, and role in the response DTO
        JwtResponse response = new JwtResponse(token, user.getUserName(), user.getUserRole());

        return new ResponseEntity<>(response, HttpStatus.OK); // 200
    }

    // --- 2. New User Registration (Add) ---
    // Maps to POST /api/user/register
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Client should not supply the userId
        if (user.getUserId() != 0) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY); // 422
        }

        // The service layer handles password encryption and insertion
        User result = userService.add(user);

        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }

        // Sanitize the password before returning the user object
        result.setPassword(null);

        return new ResponseEntity<>(result, HttpStatus.CREATED); // 201
    }

    // --- 3. Edit User Details (Update) ---
    // NOTE: In a real app, this endpoint would require a valid JWT for authorization.
    // Maps to PUT /api/user/{userId}
    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable int userId, @RequestBody User user) {
        // Ensure the ID in the URL matches the ID in the body
        if (userId != user.getUserId() || userId <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }

        boolean success = userService.update(user);
        User updatedUser = userService.findById(user.getUserId());

        if (success) {
            // Sanitize the password before returning
            updatedUser.setPassword(null);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK); // 200
        } else {
            // Could be bad ID, validation failure, or server error
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }

    // Optional: Delete method (typically restricted to admins)
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable int userId){
        // NOTE: This would typically require admin authorization
        boolean success = userService.deleteById(userId);

        if (success){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }

    }
}