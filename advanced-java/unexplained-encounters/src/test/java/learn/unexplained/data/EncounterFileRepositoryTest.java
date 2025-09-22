package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    static final String TEST_PATH = "./data/encounters-test.csv";
    final Encounter[] testEncounters = new Encounter[]{
            new Encounter(1, EncounterType.UFO, "2020-01-01", "short test #1", 1),
            new Encounter(2, EncounterType.CREATURE, "2020-02-01", "short test #2", 1),
            new Encounter(3, EncounterType.SOUND, "2020-03-01", "short test #3", 1)
    };

    EncounterRepository repository = new EncounterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws DataAccessException {
        for (Encounter e : repository.findAll()) {
            repository.deleteById(e.getEncounterId());
        }

        for (Encounter e : testEncounters) {
            repository.add(e);
        }
    }

    @Test
    void shouldFindAll() throws DataAccessException {
        List<Encounter> encounters = repository.findAll();
        Encounter[] actual = encounters.toArray(new Encounter[encounters.size()]);
        assertArrayEquals(testEncounters, actual);
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen("Jan 15, 2005");
        encounter.setDescription("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        encounter.setOccurrences(1);

        Encounter actual = repository.add(encounter);

        assertNotNull(actual);
        assertEquals(4, actual.getEncounterId());
    }

    @Test
    void shouldFindByType() throws DataAccessException {
        //Arrange + Act
        List<Encounter> ufos = repository.findByType(EncounterType.UFO);
        //Assert
        assertEquals(1, ufos.size());
        assertEquals(EncounterType.UFO, ufos.get(0).getType());
        assertEquals(1, ufos.get(0).getEncounterId());
    }

    @Test
    void shouldDelete() throws DataAccessException{
        // Act
        boolean success = repository.deleteById(2);
        //Assert
        assertTrue(success);
        List<Encounter> all = repository.findAll();
        assertEquals(2, all.size());
        assertTrue(all.stream().noneMatch(e -> e.getEncounterId() == 2));
    }

    @Test
    void shouldUpdate() throws DataAccessException{
        // Arrange
        Encounter updated = new Encounter(
                2,
                EncounterType.CREATURE,
                "2020-02-02",
                "updated description",
                3
        );
        // Act
        boolean success = repository.update(updated);
        // Assert
        assertTrue(success);
        List<Encounter> all = repository.findAll();
        assertEquals(3, all.size());
        Encounter match = all.stream()
                .filter(e -> e.getEncounterId() == 2)
                .findFirst()
                .orElse(null);
        assertNotNull(match);
        assertEquals(EncounterType.CREATURE, match.getType());
        assertEquals("2020-02-02", match.getWhen());
        assertEquals("updated description", match.getDescription());
        assertEquals(3, match.getOccurrences());
    }

}