package dndapi.models;

public class JwtResponse {
    private final String jwt;
    private final String username;
    private final String role;

    public JwtResponse(String jwt, String username, String role) {
        this.jwt = jwt;
        this.username = username;
        this.role = role;
    }

    // Getters

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}