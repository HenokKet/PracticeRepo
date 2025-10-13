package org.example.data;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.example.models.Pet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PetJdbcRepository {
    // DataSource for managing database connections
    private DataSource dataSource = initDataSource();
    /**
     * Initializes the DataSource object for database connection.
     * Uses MysqlDataSource to connect to a MySQL database.
     *
     * @return DataSource object configured with database URL, username, and
    password.
     */
    private DataSource initDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/pet_management");
        // Database connection URL
        ds.setUser("root"); // Change to your MySQL username
        ds.setPassword(""); // Change to your MySQL password
        // (avoid hardcoding in production)
        return ds;
    }
    /**
     * Adds a new Pet record to the database.
     *
     * @param pet The Pet object containing name and type.
     * @return The Pet object with an auto-generated petId if successful, null
    otherwise.
     */
    public Pet add(Pet pet) {
        final String sql = "INSERT INTO pet (name, type) VALUES (?, ?);";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {
            // Setting parameters for the prepared statement
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            // Execute the update and check if the insertion was successful
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted <= 0) return null;
            // Retrieve the auto-generated pet ID
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    pet.setPetId(keys.getInt(1));
                    return pet;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log exception details
        }
        return null;
    }
    /**
     * Retrieves all Pet records from the database.
     *
     * @return A list of Pet objects.
     */
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();
        final String sql = "SELECT pet_id, name, type FROM pet;";
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            // Process each row in the result set
            while (rs.next()) {
                Pet pet = new Pet();
                pet.setPetId(rs.getInt("pet_id"));
                pet.setName(rs.getString("name"));
                pet.setType(rs.getString("type"));
                pets.add(pet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log exception details
        }
        return pets;
    }
    /**
     * Updates an existing Pet record in the database.
     *
     * @param pet The Pet object containing updated data.
     * @return true if the update was successful, false otherwise.
     */
    public boolean update(Pet pet) {
        final String sql = "UPDATE pet SET name = ?, type = ? WHERE pet_id = ?; ";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            // Setting parameters for the prepared statement
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setInt(3, pet.getPetId());
            // Execute update and check if any rows were affected
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log exception details
        }
        return false;
    }
    /**
     * Deletes a Pet record from the database by petId.
     *
     * @param petId The ID of the pet to be deleted.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteById(int petId) {
        final String sql = "DELETE FROM pet WHERE pet_id = ?;";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            // Setting the pet ID parameter for deletion
            statement.setInt(1, petId);
            // Execute delete and check if any rows were affected
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace(); // Log exception details
        }
        return false;
    }
}
