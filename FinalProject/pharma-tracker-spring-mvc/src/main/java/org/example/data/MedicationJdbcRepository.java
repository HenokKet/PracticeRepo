package org.example.data;
import org.example.models.Medication;
import org.example.models.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * MedicationJdbcRepository
 *
 * Data access layer for medication CRUD operations using JDBC.
 * Manages interactions with the 'medications' and 'medicationlog' tables.
 * Uses Spring-managed DataSource for database connections.
 */
@Repository
public class MedicationJdbcRepository {

    // DataSource injected by Spring Boot for database connectivity
    private final DataSource dataSource;

    /**
     * Constructor with DataSource injection
     * @param dataSource Spring-managed database connection pool
     */
    public MedicationJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Retrieve all medications from the database
     *
     * @return List of all Medication objects, empty list if none found or on error
     */
    public List<Medication> findAll() {
        List<Medication> result = new ArrayList<>();
        final String sql = "SELECT ApplicationNo,user_id, medication_name, Qty, firstDosage, LastDosage, dose_interval_hours " +
                "FROM medications;";

        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            // Map each row to a Medication object
            while (rs.next()) {
                result.add(mapRowToMedication(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Find a specific medication by its application number (primary key)
     *
     * @param applicationNo The medication's unique identifier
     * @return Medication object if found, null otherwise
     */
    public Medication findById(int applicationNo) {
        final String sql = "SELECT ApplicationNo, user_id, medication_name, Qty, firstDosage, LastDosage, dose_interval_hours "
                + "FROM medications WHERE ApplicationNo = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, applicationNo);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapRowToMedication(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Find a medication by its name
     * Note: Returns only the first match if multiple medications have the same name
     *
     * @param name The medication name to search for
     * @return Medication object if found, null otherwise
     */
    public Medication findByName(String name) {
        final String sql = "SELECT ApplicationNo, user_id, medication_name, Qty, firstDosage, LastDosage, dose_interval_hours "
                + "FROM medications WHERE medication_name = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, name);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapRowToMedication(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Insert a new medication into the database
     *
     * Required fields:
     * - applicationNo: Unique identifier
     * - userId: Associated user ID
     * - medicationName: Name of the medication
     * - firstDose: Start date/time for medication schedule
     * - lastDose: End date/time for medication schedule
     * - doseIntervalHours: Hours between doses
     * - qty: Initial quantity/remaining doses
     *
     * @param medication The medication object to insert
     * @return The inserted medication if successful, null on failure
     */
    public Medication add(Medication medication) {
        final String sql = """
        INSERT INTO medications (
            ApplicationNo,
            user_id,
            medication_name,
            firstDosage,
            LastDosage,
            dose_interval_hours,
            Qty
        ) VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Set all parameter values
            statement.setInt(1, medication.getApplicationNo());
            statement.setInt(2, medication.getUserId());
            statement.setString(3, medication.getMedicationName());
            statement.setTimestamp(4, Timestamp.valueOf(medication.getFirstDose()));
            statement.setTimestamp(5, Timestamp.valueOf(medication.getLastDose()));
            statement.setInt(6, medication.getDoseIntervalHours());
            statement.setInt(7, medication.getQty());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return medication;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Update an existing medication's details
     *
     * Updates all mutable fields:
     * - medication_name
     * - Qty (remaining quantity)
     * - firstDosage (schedule start)
     * - LastDosage (schedule end)
     * - dose_interval_hours
     *
     * Identifies the record by ApplicationNo (which cannot be changed)
     *
     * @param medication The medication with updated values (must include applicationNo)
     * @return true if update successful (1+ rows affected), false otherwise
     */
    public boolean update(Medication medication) {
        final String sql = "UPDATE medications SET "
                + "medication_name = ?, "
                + "Qty = ?, "
                + "firstDosage = ?, "
                + "LastDosage = ?, "
                + "dose_interval_hours = ? "
                + "WHERE ApplicationNo = ?;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // Set updated field values
            statement.setString(1, medication.getMedicationName());
            statement.setInt(2, medication.getQty());
            statement.setTimestamp(3, Timestamp.valueOf(medication.getFirstDose()));
            statement.setTimestamp(4, Timestamp.valueOf(medication.getLastDose()));
            statement.setInt(5, medication.getDoseIntervalHours());
            statement.setInt(6, medication.getApplicationNo());

            return statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a medication and its associated logs
     *
     * Uses a transaction to ensure data integrity:
     * 1. Deletes all associated records from medicationlog table (child records)
     * 2. Deletes the medication record from medications table (parent record)
     *
     * If either operation fails, the entire transaction is rolled back.
     * This prevents orphaned log records.
     *
     * @param applicationNo The medication's unique identifier
     * @return true if deletion successful, false if medication not found or on error
     */
    public boolean deleteById(int applicationNo) {
        // SQL to delete child records (logs)
        final String deleteLogsSql =
                "DELETE FROM medicationlog WHERE ApplicationNo = ?;";
        // SQL to delete parent record (medication)
        final String deleteMedSql =
                "DELETE FROM medications WHERE ApplicationNo = ?;";

        try (Connection conn = dataSource.getConnection()) {
            // Start transaction - disable auto-commit
            conn.setAutoCommit(false);

            try (PreparedStatement deleteLogs = conn.prepareStatement(deleteLogsSql);
                 PreparedStatement deleteMed = conn.prepareStatement(deleteMedSql)) {

                // Step 1: Delete associated logs (foreign key constraint requirement)
                deleteLogs.setInt(1, applicationNo);
                deleteLogs.executeUpdate();

                // Step 2: Delete the medication record
                deleteMed.setInt(1, applicationNo);
                int rows = deleteMed.executeUpdate();

                // Commit transaction if both operations succeeded
                conn.commit();
                return rows > 0;

            } catch (SQLException ex) {
                // Rollback transaction on any error
                conn.rollback();
                ex.printStackTrace();
                return false;
            } finally {
                // Restore auto-commit mode
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Map a ResultSet row to a Medication object
     * Helper method to reduce code duplication in query methods
     *
     * @param rs ResultSet positioned at a valid row
     * @return Medication object populated with row data
     * @throws SQLException if column access fails
     */
    private Medication mapRowToMedication(ResultSet rs) throws SQLException {
        Medication medication = new Medication();
        medication.setUserId(rs.getInt("user_id"));
        medication.setMedicationName(rs.getString("medication_name"));
        medication.setApplicationNo(rs.getInt("ApplicationNo"));
        medication.setQty(rs.getInt("Qty"));
        // Convert SQL Timestamp to LocalDateTime
        medication.setFirstDose(rs.getTimestamp("firstDosage").toLocalDateTime());
        medication.setLastDose(rs.getTimestamp("LastDosage").toLocalDateTime());
        medication.setDoseIntervalHours(rs.getInt("dose_interval_hours"));
        return medication;
    }
}