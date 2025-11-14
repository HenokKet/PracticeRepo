package org.example.data;

import org.example.models.Medication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MedicationJdbcRepositoryTest {

    @Autowired
    MedicationJdbcRepository repository;

    @Test
    void shouldFindAllFourMedications() {
        List<Medication> meds = repository.findAll();

        System.out.println("Should find all meds: " + meds);

        assertNotNull(meds);

        assertEquals(5, meds.size());
    }

    @Test
    void shouldFindByNameAmoxicillin() {
        // Build expected based on your seed data
        Medication expected = new Medication();
        expected.setApplicationNo(1001);
        expected.setUserId(1);
        expected.setMedicationName("Amoxicillin");
        expected.setQty(20);
        expected.setFirstDose(LocalDateTime.of(2025, 11, 1, 8, 0, 0));
        expected.setLastDose(LocalDateTime.of(2025, 11, 10, 20, 0, 0));
        expected.setDoseIntervalHours(8);

        Medication actual = repository.findByName("Amoxicillin");

        System.out.println("Should find Amoxicillin: " + actual);

        assertNotNull(actual);
        assertEquals(expected.getApplicationNo(), actual.getApplicationNo());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getMedicationName(), actual.getMedicationName());
        assertEquals(expected.getQty(), actual.getQty());
        assertEquals(expected.getFirstDose(), actual.getFirstDose());
        assertEquals(expected.getLastDose(), actual.getLastDose());
        assertEquals(expected.getDoseIntervalHours(), actual.getDoseIntervalHours());
    }

    @Test
    void shouldNotFindMissingName() {
        Medication med = repository.findByName("NonExistentMed");
        assertNull(med);
    }

    @Test
    void shouldFindById1003() {
        // Row from your seed data: (1003, 2, 'Metformin', '2025-11-02 07:30:00', '2026-01-02 07:30:00', 24, 60)
        Medication expected = new Medication();
        expected.setApplicationNo(1003);
        expected.setUserId(2);
        expected.setMedicationName("Metformin");
        expected.setQty(60);
        expected.setFirstDose(LocalDateTime.of(2025, 11, 2, 7, 30, 0));
        expected.setLastDose(LocalDateTime.of(2026, 1, 2, 7, 30, 0));
        expected.setDoseIntervalHours(24);

        Medication actual = repository.findById(1003);

        assertNotNull(actual);
        assertEquals(expected.getApplicationNo(), actual.getApplicationNo());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getMedicationName(), actual.getMedicationName());
        assertEquals(expected.getQty(), actual.getQty());
        assertEquals(expected.getFirstDose(), actual.getFirstDose());
        assertEquals(expected.getLastDose(), actual.getLastDose());
        assertEquals(expected.getDoseIntervalHours(), actual.getDoseIntervalHours());
    }

    @Test
    void shouldNotFindMissingId() {
        Medication actual = repository.findById(9999);
        assertNull(actual);
    }

    @Test
    void shouldAdd() {
        Medication med = new Medication();
        // ApplicationNo is NOT auto-generated, so we must set a new unique value
        med.setApplicationNo(2000);
        med.setUserId(1);
        med.setMedicationName("TestMed");
        med.setQty(10);
        med.setFirstDose(LocalDateTime.of(2025, 12, 1, 9, 0, 0));
        med.setLastDose(LocalDateTime.of(2025, 12, 5, 9, 0, 0));
        med.setDoseIntervalHours(12);

        Medication actual = repository.add(med);

        System.out.println("Should add medication: " + actual);

        assertNotNull(actual);
        assertEquals(2000, actual.getApplicationNo());

        Medication retrieved = repository.findById(2000);
        assertNotNull(retrieved);
        assertEquals(med.getApplicationNo(), retrieved.getApplicationNo());
        assertEquals(med.getUserId(), retrieved.getUserId());
        assertEquals(med.getMedicationName(), retrieved.getMedicationName());
        assertEquals(med.getQty(), retrieved.getQty());
        assertEquals(med.getFirstDose(), retrieved.getFirstDose());
        assertEquals(med.getLastDose(), retrieved.getLastDose());
        assertEquals(med.getDoseIntervalHours(), retrieved.getDoseIntervalHours());
    }

    @Test
    void shouldUpdateExisting() {
        // Start from an existing medication
        Medication medToUpdate = repository.findById(1004);
        assertNotNull(medToUpdate);

        medToUpdate.setMedicationName("UpdatedLisinopril");
        medToUpdate.setQty(120);
        medToUpdate.setFirstDose(LocalDateTime.of(2025, 11, 6, 8, 0, 0));
        medToUpdate.setLastDose(LocalDateTime.of(2026, 2, 6, 8, 0, 0));
        medToUpdate.setDoseIntervalHours(12);

        assertTrue(repository.update(medToUpdate));

        Medication updated = repository.findById(1004);
        assertNotNull(updated);
        assertEquals("UpdatedLisinopril", updated.getMedicationName());
        assertEquals(120, updated.getQty());
        assertEquals(LocalDateTime.of(2025, 11, 6, 8, 0, 0), updated.getFirstDose());
        assertEquals(LocalDateTime.of(2026, 2, 6, 8, 0, 0), updated.getLastDose());
        assertEquals(12, updated.getDoseIntervalHours());
    }

    @Test
    void shouldNotUpdateMissing() {
        Medication med = new Medication();
        med.setApplicationNo(9999); // does not exist
        med.setUserId(1);
        med.setMedicationName("MissingMed");
        med.setQty(5);
        med.setFirstDose(LocalDateTime.of(2025, 11, 1, 0, 0, 0));
        med.setLastDose(LocalDateTime.of(2025, 11, 2, 0, 0, 0));
        med.setDoseIntervalHours(24);

        assertFalse(repository.update(med));
    }

    @Test
    void shouldDeleteExisting() {
        // ApplicationNo 1002 (Ibuprofen) exists in your seed data
        assertTrue(repository.deleteById(1002));

        Medication deleted = repository.findById(1002);
        assertNull(deleted);
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(9999));
    }
}
