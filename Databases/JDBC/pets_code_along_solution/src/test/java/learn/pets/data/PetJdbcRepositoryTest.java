package learn.pets.data;

import learn.pets.models.Pet;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PetJdbcRepositoryTest {
  PetJdbcRepository repository = new PetJdbcRepository();

  @Test
  @Order(1)
  void shouldFindAll(){
    List<Pet> pets = repository.findAll();
    System.out.println(pets);
    assertNotNull(pets);
    assertEquals(3, pets.size());
  }

  @Test
  @Order(2)
  void shouldFindByName() {
    Pet pet = repository.findByName("Meep");
    System.out.println(pet);
    assertNotNull(pet);
    assertEquals("Meep", pet.getName());
    assertEquals("Mouse", pet.getType());
  }

  @Test
  @Order(3)
  void shouldFindByID() {
    Pet pet = repository.findById(101);
    System.out.println(pet);
    assertNotNull(pet);
    assertEquals("Meep", pet.getName());
    assertEquals("Mouse", pet.getType());
  }

  @Test
  @Order(4)
  void shouldAdd(){
    // 1. Arrange: Create the Pet object with data to insert
    Pet pet = new Pet();
    pet.setName("Mer");
    pet.setType("Hamster");

    // 2. Act: Call the add method
    Pet actual = repository.add(pet);

    // 3. Assert: Verify the result and the stored data

    // A. Assert the returned Pet is not null and has a generated ID
    assertNotNull(actual);
    assertTrue(actual.getPetId() > 100);

    // B. Assert the returned Pet matches the input data
    assertEquals(pet.getName(), actual.getName());
    assertEquals(pet.getType(), actual.getType());

    // C. Assert the pet can be found in the database using the new ID
    Pet found = repository.findById(actual.getPetId());
    assertNotNull(found);
    assertEquals(actual, found); // Assumes Pet has a correct equals/hashCode implementation

    // 4. Cleanup (Optional, but highly recommended if not using transactional rollback)
    // Deleting the data created in this test ensures the next run is clean.
    repository.deleteByID(actual.getPetId());
  }

  @Test
  @Order(5)
  void shouldUpdateExisting() {
    // 1. Create a Pet object for testing the update
    Pet newPet = new Pet();
    newPet.setName("TestUpdate");
    newPet.setType("Placeholder");
    Pet created = repository.add(newPet); // Create the data first

    // 2. Modify the pet's data
    created.setName("Larry");
    created.setType("Lobster");

    // 3. Test the update
    assertTrue(repository.update(created));

    // 4. Verify the update
    assertEquals(created.getName(), repository.findById(created.getPetId()).getName());
    assertEquals(created.getType(), repository.findById(created.getPetId()).getType());

    // Clean up (optional if using transactional rollback)
    repository.deleteByID(created.getPetId());
  }


  @Test
  @Order(6)
  void shouldDeleteByID(){
    // 1. Arrange: Add a new Pet specifically for this delete test
    Pet petToDelete = new Pet();
    petToDelete.setName("TestDelete");
    petToDelete.setType("Temporary");

    // The repository handles setting the ID upon insertion
    Pet created = repository.add(petToDelete);

    assertNotNull(created);
    int idToDelete = created.getPetId();

    // 2. Act: Attempt to delete the Pet by its guaranteed ID
    assertTrue(repository.deleteByID(idToDelete));

    // 3. Assert: Verify the pet is actually deleted
    assertNull(repository.findById(idToDelete),
            "The pet should be null after successful deletion.");

    // Optional: Test deleting the same ID again should return false
    assertFalse(repository.deleteByID(idToDelete));
  }

}