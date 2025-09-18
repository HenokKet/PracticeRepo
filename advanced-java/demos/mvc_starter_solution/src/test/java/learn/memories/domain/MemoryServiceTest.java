package learn.memories.domain;

import learn.memories.data.DataAccessException;
import learn.memories.data.MemoryRepositoryDouble;
import learn.memories.models.Memory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryServiceTest {
    MemoryService service;

    @BeforeEach
    void setUp() {
        MemoryRepositoryDouble repository = new MemoryRepositoryDouble();
        service = new MemoryService(repository);
    }

    @Test
    void shouldFindTwoPublicMemories() throws DataAccessException {
        List<Memory> memories = service.findShareableMemories();
        assertEquals(2, memories.size());
    }

    @Test
    void shouldFindOnePrivateMemory() throws DataAccessException {
        List<Memory> memories = service.findPrivateMemories();
        assertEquals(1, memories.size());
    }

    @Test
    void shouldNotAddNullFrom() throws DataAccessException {
        Memory memory = new Memory();
        memory.setContent("This one time...");

        MemoryResult result = service.add(memory);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("From"));
    }

    @Test
    void shouldNotAddEmptyContent() throws DataAccessException {
        Memory memory = new Memory();
        memory.setFrom("Ant");

        MemoryResult result = service.add(memory);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("Content"));
    }

    @Test
    void shouldNotAddPositiveID() throws DataAccessException {
        Memory memory = new Memory(12, "From12", "Content12", true);

        MemoryResult result = service.add(memory);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("ID"));

    }

    @Test
    void shouldAdd() throws DataAccessException {
        Memory memory = new Memory();
        memory.setFrom("Vito Corleone");
        memory.setContent("I made them an offer...");

        MemoryResult result = service.add(memory);
        assertTrue(result.isSuccess());

    }

    @Test
    void shouldNotUpdateEmptyContent() throws DataAccessException {
        Memory memory = service.findShareableMemories().get(0);
        memory.setContent(null);

        MemoryResult result = service.update(memory);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("Content"));
    }

    @Test
    void shouldUpdate() throws DataAccessException {
        Memory memory = service.findShareableMemories().get(0);
        memory.setContent("Updated Content");
        MemoryResult result =service.update(memory);

        assertTrue(result.isSuccess());
    }

}