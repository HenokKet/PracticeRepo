import org.example.data.PetJdbcRepository;
import org.example.models.Pet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PetJdbcRepositoryTest {
    PetJdbcRepository repository = new PetJdbcRepository();
    @Test
    void shouldAddPet() {
        Pet pet = new Pet();
        pet.setName("Charlie");
        pet.setType("Parrot");
        Pet actual = repository.add(pet);
        assertNotNull(actual);
        assertTrue(actual.getPetId() > 0);
    }
    @Test
    void shouldFindAllPets() {
        List<Pet> pets = repository.findAll();
        assertNotNull(pets);
        assertTrue(pets.size() > 0);
    }
    @Test
    void shouldUpdatePet() {
        Pet pet = repository.findAll().get(0);
        pet.setName("Updated Name");
        assertTrue(repository.update(pet));
        assertEquals("Updated Name", repository.findAll().get(0).getName());
    }
    @Test
    void shouldDeletePet() {
        Pet p = new Pet();
        p.setName("Temp Pet");
        p.setType("Lizard");
        Pet pet = repository.add(p);
        assertTrue(repository.deleteById(pet.getPetId()));
    }
}
