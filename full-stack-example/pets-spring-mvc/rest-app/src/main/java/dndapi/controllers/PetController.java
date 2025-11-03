package dndapi.controllers;

import dndapi.data.PetJdbcRepository;
import dndapi.models.Pet;
import dndapi.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
@RestController
@RequestMapping("api/pet")
public class PetController {

    private final PetJdbcRepository repository;
    private final JwtService jwtService;

    public PetController(PetJdbcRepository repository, JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    // Helper method to validate the JWT and check for 'Bearer ' prefix
    private boolean validateToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }

        String token = authorizationHeader.substring(7);

        // This relies on the JwtService placeholder for validation logic.
        // A real JwtService would verify the signature, claims, and expiration here.
        return token.startsWith("fake-jwt-");
    }

    // --- ALL methods now require a valid JWT via the Authorization header ---

    @GetMapping
    public ResponseEntity<List<Pet>> findAll(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getPetTypes(
            @RequestHeader(name = "Authorization", required = false) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        return new ResponseEntity<>(repository.getPetTypes(), HttpStatus.OK);
    }

    @GetMapping("/{petID}")
    public ResponseEntity<Pet> findById(
            @PathVariable int petID,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        Pet pet = repository.findById(petID);
        if(pet == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404
        }

        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @GetMapping("/name/{petName}")
    public ResponseEntity<Pet> findByName(
            @PathVariable String petName,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        Pet pet = repository.findByName(petName);
        if (pet == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pet> add(
            @RequestBody Pet pet,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        if (pet.getPetId() !=0){
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY); // 422
        }

        Pet result = repository.add(pet);
        if (result == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED); // 201
    }

    @PutMapping()
    public ResponseEntity<Pet> update(
            @RequestBody Pet pet,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        boolean success = repository.update(pet);

        if (success) {
            Pet updatedPet = repository.findById(pet.getPetId());
            return new ResponseEntity<>(updatedPet, HttpStatus.OK); // 200
        } else {
            // Assuming failure means resource not found or bad input
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
        }
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deleteById(
            @PathVariable int petId,
            @RequestHeader(name = "Authorization", required = true) String authorizationHeader) {

        if (!validateToken(authorizationHeader)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
        }

        boolean success = repository.deleteById(petId);

        if (success){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } else {
            // If deletion fails, it usually means the ID was not found.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }
}