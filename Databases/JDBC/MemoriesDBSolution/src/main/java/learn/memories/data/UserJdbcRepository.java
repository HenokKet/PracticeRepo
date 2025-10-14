package learn.memories.data;

import learn.memories.data.mappers.UserMapper;
import learn.memories.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final String ENCRYPTION_KEY = "carrot"; // The reversible key

    // Dependency Injection via constructor
    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // --- Helper Method ---
    // Defines the standard SELECT query for the User table, using AES_DECRYPT.
    private String getSelectQuery() {
        // CRITICAL: We use AES_DECRYPT and cast the resulting VARBINARY back to CHAR
        // and alias it as 'password' for the UserMapper to read the plaintext string.
        return String.format(
                "SELECT user_id, user_name, CAST(AES_DECRYPT(password_aes, '%s') AS CHAR) AS password FROM user",
                ENCRYPTION_KEY
        );
    }

    // --- CRUD Operations ---

    @Override
    public List<User> findAll() throws DataAccessException {
        final String sql = getSelectQuery() + ";";
        UserMapper mapper = new UserMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public User findById(int userId) throws DataAccessException {
        final String sql = getSelectQuery() + " WHERE user_id = ?;";
        UserMapper mapper = new UserMapper();
        try {
            return jdbcTemplate.queryForObject(sql, mapper, userId);
        } catch (EmptyResultDataAccessException ex) {
            return null; // User not found
        }
    }

    @Override
    public User findByUsername(String username) throws DataAccessException {
        final String sql = getSelectQuery() + " WHERE user_name = ?;";
        UserMapper mapper = new UserMapper();
        try {
            return jdbcTemplate.queryForObject(sql, mapper, username);
        } catch (EmptyResultDataAccessException ex) {
            return null; // User not found
        }
    }

    @Override
    public User add(User user) throws DataAccessException {
        // CRITICAL: We use AES_ENCRYPT in the SQL INSERT statement.
        final String sql = String.format(
                "INSERT INTO user (user_name, password_aes) VALUES (?, AES_ENCRYPT(?, '%s'));",
                ENCRYPTION_KEY
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 1. user_name
            ps.setString(1, user.getUserName());

            // 2. Convert the password (which is byte[]) back to a String for AES_ENCRYPT to process in SQL.
            String passwordString = new String(user.getPassword(), StandardCharsets.UTF_8);
            ps.setString(2, passwordString);

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null; // Add failed
        }

        // Set the generated ID on the User object
        user.setUserId(keyHolder.getKey().intValue());

        // Returns the object with the new ID
        return user;
    }

    @Override
    public boolean update(User user) throws DataAccessException {
        // CRITICAL: We use AES_ENCRYPT in the SQL UPDATE statement.
        final String sql = String.format(
                "UPDATE user SET user_name = ?, password_aes = AES_ENCRYPT(?, '%s') WHERE user_id = ?;",
                ENCRYPTION_KEY
        );

        int rowsAffected = jdbcTemplate.update(sql,
                user.getUserName(),    // 1. user_name
                user.getPassword(),    // 2. plaintext password (String) passed to AES_ENCRYPT
                user.getUserId()       // 3. user_id (WHERE clause)
        );

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteById(int userId) throws DataAccessException {
        final String sql = "DELETE FROM user WHERE user_id = ?;";
        int rowsAffected = jdbcTemplate.update(sql, userId);

        return rowsAffected > 0;
    }
}
