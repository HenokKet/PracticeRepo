package learn.pets.data;

import learn.pets.models.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 1. Loads the full Spring Boot application context for testing.
@SpringBootTest
// 2. Ensures every test runs inside a transaction and rolls back changes
//    after completion, guaranteeing a clean database state for the next test.
@Transactional
class PetJdbcRepositoryTest {

    // 3. Spring injects the component instance (PetJdbcRepository)
    //    which was configured with the DataSource from application.properties.
    @Autowired
    PetJdbcRepository repository;

    @Test
    void shouldFindAllThreePets() {
        List<Pet> pets = repository.findAll();

        System.out.println("Should find all: " + pets);

        assertNotNull(pets);
        // Assumes initial SQL script leaves 3 pets
        assertEquals(3, pets.size());
    }

    @Test
    void shouldFindByNameMeep() {
        Pet expected = new Pet();
        expected.setPetId(1);
        expected.setName("Meep");
        expected.setType("Mouse");

        Pet actual = repository.findByName("Meep");

        System.out.println("Should find Meep: " + actual);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindMissingName() {
        Pet pet = repository.findByName("Sparky");
        assertNull(pet);
    }

    @Test
    void shouldFindByIdThree() {
        Pet expected = new Pet();
        expected.setPetId(3);
        expected.setName("Noodles");
        expected.setType("Dog");

        Pet actual = repository.findById(3);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindMissingId() {
        Pet actual = repository.findById(999);
        assertNull(actual);
    }

    @Test
    void shouldAdd() {
        Pet pet = new Pet();
        pet.setName("Mer");
        pet.setType("Hamster");

        Pet actual = repository.add(pet);

        System.out.println("Should add pet: " + actual);

        assertNotNull(actual);
        // Pet ID should be generated and greater than 0
        assertTrue(actual.getPetId() > 0);

        // Verify it can be retrieved using the generated ID
        Pet retrieved = repository.findById(actual.getPetId());
        assertEquals(actual, retrieved);
    }

    @Test
    void shouldUpdateExisting() {
        // Retrieve an existing pet to modify
        Pet petToUpdate = repository.findById(2);
        petToUpdate.setName("Singe");
        petToUpdate.setType("Python");

        assertTrue(repository.update(petToUpdate));

        // Verify the change in the database
        Pet updatedPet = repository.findById(2);
        assertEquals("Singe", updatedPet.getName());
        assertEquals("Python", updatedPet.getType());
    }

    @Test
    void shouldNotUpdateMissing() {
        Pet pet = new Pet();
        pet.setPetId(20000); // ID that doesn't exist
        pet.setName("Missing");
        pet.setType("Creature");

        assertFalse(repository.update(pet));
    }

    @Test
    void shouldDeleteExisting() {
        // ID 1 (Meep) should exist
        assertTrue(repository.deleteById(1));

        // Verify it is gone
        assertNull(repository.findById(1));
    }

    @Test
    void shouldNotDeleteMissing() {
        assertFalse(repository.deleteById(40000));
    }

}
