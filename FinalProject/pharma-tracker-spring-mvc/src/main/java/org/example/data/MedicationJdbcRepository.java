package org.example.data;
import org.example.models.Medication;
import org.example.models.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 1. Annotate as a Spring repository component
@Repository
public class MedicationJdbcRepository {
    // 2. The DataSource is now injected by Spring Boot
    private final DataSource dataSource;

    public MedicationJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Medication> findAll() {
        List<Medication> result = new ArrayList<>();
        final String sql = "SELECT ApplicationNo,user_id, medication_name, Qty, firstDosage, LastDosage, dose_interval_hours " +
                "FROM medications;";
        try (Connection conn = dataSource.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                result.add(mapRowToMedication(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

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

    // --- Add New Medication ---
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


    // --- Update Medication ---
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

    public boolean deleteById(int applicationNo) {
        final String deleteLogsSql =
                "DELETE FROM medicationlog WHERE ApplicationNo = ?;";
        final String deleteMedSql =
                "DELETE FROM medications WHERE ApplicationNo = ?;";

        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteLogs = conn.prepareStatement(deleteLogsSql);
                 PreparedStatement deleteMed = conn.prepareStatement(deleteMedSql)) {

                // 1) delete logs
                deleteLogs.setInt(1, applicationNo);
                deleteLogs.executeUpdate();

                // 2) delete medication
                deleteMed.setInt(1, applicationNo);
                int rows = deleteMed.executeUpdate();

                conn.commit();
                return rows > 0;
            } catch (SQLException ex) {
                conn.rollback();
                ex.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private Medication mapRowToMedication(ResultSet rs) throws SQLException {
        Medication medication = new Medication();
        medication.setUserId(rs.getInt("user_id"));
        medication.setMedicationName(rs.getString("medication_name"));
        medication.setApplicationNo(rs.getInt("ApplicationNo"));
        medication.setQty(rs.getInt("Qty"));
        medication.setFirstDose(rs.getTimestamp("firstDosage").toLocalDateTime());
        medication.setLastDose(rs.getTimestamp("LastDosage").toLocalDateTime());
        medication.setDoseIntervalHours(rs.getInt("dose_interval_hours"));
        return medication;
    }
}
