package dndapi.service;

import dndapi.models.User;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    // This is a simple placeholder for JWT generation.
    // In a real application, this would contain complex logic
    // using libraries like JJWT to sign and encode tokens.
    private static final String FAKE_SECRET_KEY = "YourActualJwtSecretKeyHere";

    public String generateToken(User user) {
        // In a real implementation, you'd create a secure JWT here.
        // For demonstration, we'll return a simple encoded string.
        String payload = user.getUserId() + ":" + user.getUserName() + ":" + user.getUserRole();

        // This simulates a token being generated.
        return "fake-jwt-" + payload + "-" + System.currentTimeMillis();
    }
}