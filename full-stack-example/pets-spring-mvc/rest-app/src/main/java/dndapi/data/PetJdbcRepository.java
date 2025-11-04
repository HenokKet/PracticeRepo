package dndapi.data;

import dndapi.models.Pet;
import org.springframework.stereotype.Repository; // Import Spring's Repository annotation

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// 1. Annotate as a Spring repository component
@Repository
public class PetJdbcRepository {

    // 2. The DataSource is now injected by Spring Boot
    private final DataSource dataSource;

    // 3. Constructor for dependency injection. Spring Boot automatically finds
    //    and provides the DataSource configured in application.properties.
    public PetJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getPetTypes() {
        List<String> petTypes = new ArrayList<>();
        final String sql = "SELECT Distinct(type) FROM pets.pet order by type;";
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                petTypes.add(rs.getString("type"));
            }
        } catch (SQLException ex) {
            // In a real application, you might use a logger and throw a custom
            // data access exception instead of printing the stack trace.
            ex.printStackTrace();
        }
        return petTypes;
    }

    public List<Pet> findAll() {
        ArrayList<Pet> result = new ArrayList<>();
        final String sql = "select pet_id, `name`, `type` from pet;";

        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                result.add(mapRowToPet(rs));
            }
        } catch (SQLException ex) {
            // In a real application, you might use a logger and throw a custom
            // data access exception instead of printing the stack trace.
            ex.printStackTrace();
        }
        return result;
    }

    public Pet findByName(String petName) {
        // IMPORTANT: Use PreparedStatement for all dynamic queries to prevent SQL injection!
        final String sql = "select pet_id, `name`, `type` from pet where name = ?;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, petName);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPet(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Pet findById(int petId) {
        final String sql = "select pet_id, `name`, `type` from pet where pet_id = ?;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, petId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPet(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Pet add(Pet pet) {
        final String sql = "insert into pet (`name`, `type`) values (?, ?);";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted <= 0) {
                return null;
            }

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    pet.setPetId(keys.getInt(1));
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pet;
    }

    public boolean update(Pet pet) {
        final String sql = "update pet set "
                + "`name` = ?, "
                + "`type` = ? "
                + "where pet_id = ?;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setInt(3, pet.getPetId());

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int petId) {
        final String sql = "delete from pet where pet_id = ?;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, petId);

            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Helper method to map the current row of the ResultSet to a Pet object
    private Pet mapRowToPet(ResultSet rs) throws SQLException {
        Pet pet = new Pet();
        pet.setPetId(rs.getInt("pet_id"));
        pet.setName(rs.getString("name"));
        pet.setType(rs.getString("type"));
        return pet;
    }
}
