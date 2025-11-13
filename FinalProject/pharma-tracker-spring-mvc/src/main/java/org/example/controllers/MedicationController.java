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

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RestController
@RequestMapping("api/pharma")
public class MedicationController {

    private final MedicationJdbcRepository repository;
    private final JwtService jwtService;
    private final UserService userService;
    private User loggedInUser;

    public MedicationController(MedicationJdbcRepository repository, JwtService jwtService,UserService userService) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    // Helper method to validate the JWT and check for 'Bearer ' prefix
//    private boolean validateToken(String authorizationHeader) {
//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//            return false;
//        }
//        String token = authorizationHeader.substring(7);
//        // Placeholder logic; a real JwtService should validate signature/claims/exp.
//        return token.startsWith("fake-jwt-");
//    }
    private boolean validateToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }
        //Extract the Token
        String token = authorizationHeader.substring(7);

        //Extract the user Name
        String delimiter = ":";
        String[] parts = token.split(delimiter);
        String loggedInUserName = "Extraction Failed";

        // Check if the array has enough elements to safely extract the second part (index 1).
        if (parts.length > 1) {
            // Extract the element at index 1, which represents the username.
            loggedInUserName = parts[1].substring(0,parts[1].indexOf("-"));
            loggedInUser = userService.findByUsername(loggedInUserName);
        } else {
            return false;
        }
        // Placeholder for real validation logic:
        // A real JwtService would verify the signature, claims, and expiration here.
        return token.startsWith("fake-jwt-");
    }
    // --- ALL endpoints require a valid JWT via the Authorization header ---

    @GetMapping
    public ResponseEntity<List<Medication>> findAll(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

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

    @PostMapping
    public ResponseEntity<Medication> add(
            @RequestBody Medication medication,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        if (medication.getApplicationNo() <= 0
                || medication.getMedicationName() == null || medication.getMedicationName().isBlank()) {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY); // 422
        }
        medication.setUserId(loggedInUser.getUserId());
        Medication result = repository.add(medication);
        if (result == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED); // 201
    }

    @PutMapping
    public ResponseEntity<Medication> update(
            @RequestBody Medication medication,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        // Require a valid primary key to update
        if (medication.getApplicationNo() <= 0) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); // 422
        }

        boolean success = repository.update(medication);

        if (success) {
            Medication updated = repository.findById(medication.getApplicationNo());
            return new ResponseEntity<>(updated, HttpStatus.OK); // 200
        } else {
            // Not found or invalid update
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }
    }

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