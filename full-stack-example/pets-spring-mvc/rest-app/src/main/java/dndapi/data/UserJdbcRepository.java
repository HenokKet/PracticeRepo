package dndapi.data;

import dndapi.models.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserJdbcRepository {

    private final DataSource dataSource;
    // Hardcode the secret key as requested
    private static final String SECRET_KEY = "carrot";

    public UserJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // --- READ Operations ---

    public List<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
        // Use AES_DECRYPT to get the plaintext password for mapping
        final String sql = "SELECT user_id, user_name, user_email, user_role, "
                + "CAST(AES_DECRYPT(password_aes, ?) AS CHAR) as password "
                + "FROM user;";

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
        final String sql = "SELECT user_id, user_name, user_email, user_role, "
                + "CAST(AES_DECRYPT(password_aes, ?) AS CHAR) as password "
                + "FROM user WHERE user_id = ?;";

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

    // --- CREATE Operation ---

    public User add(User user) {
        // Use AES_ENCRYPT to encrypt the plaintext password before insertion
        final String sql = "INSERT INTO user (user_name, user_email, user_role, password_aes) "
                + "VALUES (?, ?, ?, AES_ENCRYPT(?, ?));";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserEmail());
            statement.setString(3, user.getUserRole());
            statement.setString(4, user.getPassword()); // Plaintext password
            statement.setString(5, SECRET_KEY);

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
        // Use AES_ENCRYPT to encrypt the new plaintext password before updating
        final String sql = "UPDATE user SET "
                + "user_name = ?, "
                + "user_email = ?, "
                + "user_role = ?, "
                + "password_aes = AES_ENCRYPT(?, ?) "
                + "WHERE user_id = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserEmail());
            statement.setString(3, user.getUserRole());
            statement.setString(4, user.getPassword()); // Plaintext password
            statement.setString(5, SECRET_KEY);
            statement.setInt(6, user.getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // --- DELETE Operation ---

    public boolean deleteById(int userId) {
        final String sql = "DELETE FROM user WHERE user_id = ?;";
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
        user.setUserRole(rs.getString("user_role"));
        // The password column in the result set is a plaintext string
        user.setPassword(rs.getString("password"));
        return user;
    }
}