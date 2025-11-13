package org.example.models;

public class JwtResponse {
    private final String jwt;
    private final String username;
    private String firstName;
    private String lastName;

    public JwtResponse(String jwt, String username, String firstName, String lastName) {
        this.jwt = jwt;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {return firstName; }

    public String getLastName() { return lastName;}


}
