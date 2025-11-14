package org.example.controllers;

import org.example.data.MedicationJdbcRepository;
import org.example.domain.JwtService;
import org.example.domain.UserService;
import org.example.models.Medication;
import org.example.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MedicationController
 *
 * REST API controller for managing medications.
 * Provides CRUD operations for medications with JWT-based authentication.
 * All endpoints require a valid Bearer token in the Authorization header.
 *
 * Base path: /api/pharma
 */
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RestController
@RequestMapping("api/pharma")
public class MedicationController {

    private final MedicationJdbcRepository repository;
    private final JwtService jwtService;
    private final UserService userService;

    // Stores the currently authenticated user extracted from JWT
    private User loggedInUser;

    /**
     * Constructor with dependency injection
     * @param repository Medication data access layer
     * @param jwtService JWT token service (currently unused but available for future enhancement)
     * @param userService Service for user lookup and authentication
     */
    public MedicationController(MedicationJdbcRepository repository, JwtService jwtService, UserService userService) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    /**
     * Validate JWT token from Authorization header and extract logged-in user
     *
     * Token format expected: "Bearer fake-jwt-{username}-{timestamp}"
     * This is a simplified implementation using a fake JWT format.
     *
     * Process:
     * 1. Checks for "Bearer " prefix
     * 2. Extracts token after "Bearer "
     * 3. Parses username from token (between first ":" and first "-" after colon)
     * 4. Looks up user by username
     * 5. Validates token starts with "fake-jwt-"
     *
     * @param authorizationHeader The Authorization header value (e.g., "Bearer fake-jwt-...")
     * @return true if token is valid and user exists, false otherwise
     *
     * Note: This is a placeholder implementation. Production code should use proper
     * JWT validation with signature verification, expiration checking, and claim validation.
     */
    private boolean validateToken(String authorizationHeader) {
        // Check for Authorization header and Bearer prefix
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }

        // Extract token (remove "Bearer " prefix)
        String token = authorizationHeader.substring(7);

        // Parse username from token format
        String delimiter = ":";
        String[] parts = token.split(delimiter);
        String loggedInUserName = "Extraction Failed";

        // Extract username from token structure
        if (parts.length > 1) {
            // Get username (second part, before the first hyphen)
            loggedInUserName = parts[1].substring(0, parts[1].indexOf("-"));
            // Look up user and store for request context
            loggedInUser = userService.findByUsername(loggedInUserName);
        } else {
            return false;
        }

        // Placeholder validation - checks token format
        // TODO: Replace with proper JWT signature/claims/expiration validation
        return token.startsWith("fake-jwt-");
    }

    // --- CRUD Endpoints (All require JWT authentication) ---

    /**
     * GET /api/pharma
     * Retrieve all medications for the authenticated user
     *
     * @param authorizationHeader JWT token (Bearer {token})
     * @return 200 OK with list of medications, or 401 UNAUTHORIZED if invalid token
     */
    @GetMapping
    public ResponseEntity<List<Medication>> findAll(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    /**
     * GET /api/pharma/{applicationNo}
     * Retrieve a specific medication by its application number
     *
     * @param applicationNo The medication's unique identifier
     * @param authorizationHeader JWT token (Bearer {token})
     * @return 200 OK with medication if found,
     *         404 NOT_FOUND if medication doesn't exist,
     *         401 UNAUTHORIZED if invalid token
     */
    @GetMapping("/{applicationNo}")
    public ResponseEntity<Medication> findById(
            @PathVariable int applicationNo,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        Medication med = repository.findById(applicationNo);
        if (med == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(med, HttpStatus.OK);
    }

    /**
     * GET /api/pharma/name/{medicationName}
     * Retrieve a medication by its name
     *
     * @param medicationName The medication's name (URL encoded)
     * @param authorizationHeader JWT token (Bearer {token})
     * @return 200 OK with medication if found,
     *         404 NOT_FOUND if medication doesn't exist,
     *         401 UNAUTHORIZED if invalid token
     */
    @GetMapping("/name/{medicationName}")
    public ResponseEntity<Medication> findByName(
            @PathVariable String medicationName,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        Medication med = repository.findByName(medicationName);
        if (med == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(med, HttpStatus.OK);
    }

    /**
     * POST /api/pharma
     * Create a new medication
     *
     * Automatically associates the medication with the authenticated user by setting userId.
     *
     * @param medication The medication object to create (from request body)
     * @param authorizationHeader JWT token (Bearer {token})
     * @return 201 CREATED with the created medication,
     *         422 UNPROCESSABLE_ENTITY if validation fails (invalid applicationNo or missing name),
     *         500 INTERNAL_SERVER_ERROR if creation fails,
     *         401 UNAUTHORIZED if invalid token
     */
    @PostMapping
    public ResponseEntity<Medication> add(
            @RequestBody Medication medication,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        // Validate required fields
        if (medication.getApplicationNo() <= 0
                || medication.getMedicationName() == null || medication.getMedicationName().isBlank()) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY); // 422
        }

        // Associate medication with authenticated user
        medication.setUserId(loggedInUser.getUserId());
        Medication result = repository.add(medication);

        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED); // 201
    }

    /**
     * PUT /api/pharma
     * Update an existing medication
     *
     * Requires the medication object to include a valid applicationNo (primary key).
     *
     * @param medication The medication object with updated values (from request body)
     * @param authorizationHeader JWT token (Bearer {token})
     * @return 200 OK with updated medication if successful,
     *         422 UNPROCESSABLE_ENTITY if applicationNo is invalid (<=0),
     *         400 BAD_REQUEST if update fails or medication not found,
     *         401 UNAUTHORIZED if invalid token
     */
    @PutMapping
    public ResponseEntity<Medication> update(
            @RequestBody Medication medication,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        // Require valid primary key for update operation
        if (medication.getApplicationNo() <= 0) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); // 422
        }

        boolean success = repository.update(medication);

        if (success) {
            // Fetch and return the updated medication
            Medication updated = repository.findById(medication.getApplicationNo());
            return new ResponseEntity<>(updated, HttpStatus.OK); // 200
        } else {
            // Update failed - medication not found or invalid data
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }
    }

    /**
     * DELETE /api/pharma/{applicationNo}
     * Delete a medication by its application number
     *
     * @param applicationNo The medication's unique identifier
     * @param authorizationHeader JWT token (Bearer {token})
     * @return 204 NO_CONTENT if deletion successful,
     *         404 NOT_FOUND if medication doesn't exist,
     *         401 UNAUTHORIZED if invalid token
     */
    @DeleteMapping("/{applicationNo}")
    public ResponseEntity<Void> deleteById(
            @PathVariable int applicationNo,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        boolean success = repository.deleteById(applicationNo);
        if (success) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }
}