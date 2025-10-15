package learn.memories.models; // Adjust the package to match your project structure

import java.util.Objects;

public class User {

    private int userId;
    private String userName;
    private byte[] password; // Use byte[] to store VARBINARY data

    public User() {
    }

    public User(int userId, String userName, byte[] passwordAes) {
        this.userId = userId;
        this.userName = userName;
        this.password = passwordAes;
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    // Standard POJO Overrides (Recommended)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(userName, user.userName) &&
                java.util.Arrays.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, java.util.Arrays.hashCode(password));
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", passwordAes=[***ENCRYPTED***]" +
                '}';
    }
}