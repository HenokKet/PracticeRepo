package learn.memories.data;

import learn.memories.data.mappers.MemoryMapper;
import learn.memories.models.Memory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MemoryJdbcRepository implements MemoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemoryJdbcRepository (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Memory> findAll() throws DataAccessException {
        // 1. Define the SQL query
        final String sql = getSelectQuery() + ";";

        // 2. Create an instance of the MemoryMapper
        MemoryMapper mapper = new MemoryMapper();

        // 3. Use jdbcTemplate.query() with the custom mapper
        // This method executes the SQL and uses the mapper to convert each row
        // into a Memory object, collecting them into a List.
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Memory findById(int memoryId) throws DataAccessException {
        // 1. Define the SQL query
        final String sql = getSelectQuery() + " WHERE memory.memory_id = ?;";

        // 2. Create an instance of the MemoryMapper
        MemoryMapper mapper = new MemoryMapper();

        // 3. Catch EmptyResultDataAccessException
        try {
            // Use the correct signature: (sql, mapper, parameter_values)
            return jdbcTemplate.queryForObject(sql, mapper, memoryId);
        } catch (EmptyResultDataAccessException ex) {
            // If the query returns 0 rows, catch the exception and return null.
            return null;
        }
    }

    @Override
    public List<Memory> findShareable(boolean shareable) throws DataAccessException{
        // 1. Define the SQL query
        final String sql = getSelectQuery() +
                " WHERE is_public = true;";

        // 2. Create an instance of the MemoryMapper
        MemoryMapper mapper = new MemoryMapper();

        // 3. Use jdbcTemplate.query() with the custom mapper
        // This method executes the SQL and uses the mapper to convert each row
        // into a Memory object, collecting them into a List.
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Memory add(Memory memory) throws DataAccessException{
        final String sql = "INSERT INTO memory (user_id_created, date_time_created, memoryText, is_public, averageTemp) "
                + " VALUES (?, NOW(), ?, ?,?);"; // Note: Assuming date_time_created is set by the database (NOW())

        // Use KeyHolder to capture the auto-generated primary key (memory_id)
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 1. user_id_created (NOTE: Assuming memory object has a userId)
            ps.setInt(1, memory.getUserID());
            // 2. memoryText
            ps.setString(2, memory.getContent());
            // 3. is_public (BIT column)
            ps.setBoolean(3, memory.isShareable());
            ps.setDouble(4, memory.getAverageTemp());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null; // Add failed
        }

        // Set the generated ID on the Memory object
        memory.setId(keyHolder.getKey().intValue());

        // NOTE: The `date_time_created` and `user_name` fields aren't set here.
        // To get the full, correct object, you might call findById(memory.getId())
        return memory;
    }


    @Override
    public boolean update(Memory memory) throws DataAccessException{
        final String sql = "UPDATE memory SET "
                + "memoryText = ?, "
                + "is_public = ? "
                + "WHERE memory_id = ?;";

        // NOTE: user_id_created and date_time_created are typically not updated.

        int rowsAffected = jdbcTemplate.update(sql,
                memory.getContent(), // 1. memoryText
                memory.isShareable(),   // 2. is_public (BIT column)
                memory.getId()          // 3. memory_id (WHERE clause)
        );

        // Returns true if exactly one row was updated
        return rowsAffected > 0;
    }


    @Override
    public boolean deleteById(int memoryId) throws DataAccessException{
        final String sql = "DELETE FROM memory WHERE memory_id = ?;";

        int rowsAffected = jdbcTemplate.update(sql, memoryId);

        // Returns true if at least one row was deleted
        return rowsAffected > 0;
    }


    private String getSelectQuery(){
        return "SELECT memory.memory_id, " +
                "memory.user_id_created, " +
                "user.user_name, " +
                "memory.date_time_created, " +
                "memory.memoryText, " +
                "memory.is_public, " +
                "memory.averageTemp " +
                "FROM memories.memory " +
                "INNER JOIN memories.user " +
                "ON memory.user_id_created = user.user_id";
    }
}
