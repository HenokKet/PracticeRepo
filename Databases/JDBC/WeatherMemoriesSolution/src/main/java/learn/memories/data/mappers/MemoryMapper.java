package learn.memories.data.mappers;

import learn.memories.models.Memory;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemoryMapper implements RowMapper<Memory> {

    @Override
    public Memory mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Memory memory = new Memory();
        // Map the columns from the ResultSet to the Memory object properties
        memory.setId(resultSet.getInt("memory_id"));
        memory.setUserID(resultSet.getInt("user_id_created"));
        memory.setShareable(resultSet.getBoolean("is_public"));
        memory.setFrom(resultSet.getString("user_name"));
        memory.setContent(resultSet.getString("memoryText"));
        return memory;
    }
}
