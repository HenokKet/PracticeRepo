package org.example.data;

import org.example.models.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository {

    private final DataSource dataSource;

    // Hardcode the secret key as requested
    private static final String SECRET_KEY = "1234";

    public UserJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // --- READ Operations ---

    public List<User> findAll() {
        ArrayList<User> result = new ArrayList<>();

        final String sql =
                "SELECT user_id, user_name, user_email, first_name, last_name, " +
                        "       CAST(AES_DECRYPT(password_aes, ?) AS CHAR) AS password " +
                        "FROM medUser;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, SECRET_KEY);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    result.add(mapRowToUser(rs));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public User findById(int userId) {
        final String sql =
                "SELECT user_id, user_name, user_email, first_name, last_name, " +
                        "       CAST(AES_DECRYPT(password_aes, ?) AS CHAR) AS password " +
                        "FROM medUser " +
                        "WHERE user_id = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, SECRET_KEY);
            statement.setInt(2, userId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User findByUsername(String Username) {
        final String sql =
                "SELECT user_id, user_name, user_email, first_name, last_name, " +
                        "       CAST(AES_DECRYPT(password_aes, ?) AS CHAR) AS password " +
                        "FROM medUser " +
                        "WHERE user_name = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, SECRET_KEY);
            statement.setString(2, Username);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public User add(User user) {
        final String sql =
                "INSERT INTO medUser (user_name, user_email, first_name, last_name, password_aes) " +
                        "VALUES (?, ?, ?, ?, AES_ENCRYPT(?, ?));";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserEmail());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword()); // plaintext here; encrypted by AES_ENCRYPT
            statement.setString(6, SECRET_KEY);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted <= 0) {
                return null;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setUserId(keys.getInt(1));
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    // --- UPDATE Operation ---

    public boolean update(User user) {
        final String sql =
                "UPDATE medUser SET " +
                        "  user_name = ?, " +
                        "  user_email = ?, " +
                        "  first_name = ?, " +
                        "  last_name = ?, " +
                        "  password_aes = AES_ENCRYPT(?, ?) " +
                        "WHERE user_id = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserEmail());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getPassword()); // plaintext; encrypted in SQL
            statement.setString(6, SECRET_KEY);
            statement.setInt(7, user.getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // --- DELETE Operation ---

    public boolean deleteById(int userId) {
        final String sql = "DELETE FROM medUser WHERE user_id = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // --- Helper Method ---

    private User mapRowToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        user.setUserEmail(rs.getString("user_email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPassword(rs.getString("password")); // plaintext obtained via AES_DECRYPT alias
        return user;
    }
}
