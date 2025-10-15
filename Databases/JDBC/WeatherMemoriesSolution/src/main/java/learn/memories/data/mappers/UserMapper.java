package learn.memories.data.mappers;

import learn.memories.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUserName(rs.getString("user_name"));
        // Maps the decrypted password, retrieving it as a byte array
        user.setPassword(rs.getBytes("password"));
        return user;
    }
}
