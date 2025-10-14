package learn.memories.domain; // Use the correct package for your test

import learn.memories.data.DataAccessException;
import learn.memories.models.Memory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 1. Use the SpringJUnitConfig annotation
// (or @ExtendWith(SpringExtension.class) + @ContextConfiguration)
@SpringJUnitConfig
@ContextConfiguration(locations = {"classpath:dependency-configuration.xml"}) // 2. Point to your XML file
class MemoryServiceIntegrationTest {

    // 3. Autowire the production service bean created by Spring
    @Autowired
    MemoryService service;

    // The @BeforeEach method is no longer needed since Spring handles setup.

    @Test
    void shouldFindAll() throws DataAccessException {
        // NOTE: For this test to pass, your configured database must contain exactly 10 records.
        // It's usually better to test for a successful read (size > 0)
        // and test specific data in other tests.

        List<Memory> memories = service.findAllMemories();

        // Assert that we successfully read data from the database
        //assertTrue(memories.size() > 0, "Should return records from the JDBC repository.");

        // If you are confident your test data setup is exactly 10:
        assertEquals(11, memories.size());
    }

    @Test
    void shouldFindByShareable() throws DataAccessException {

        List<Memory> memories = service.findShareableMemories();

        // Assert that we successfully read data from the database
        //assertTrue(memories.size() > 0, "Should return records from the JDBC repository.");

        // If you are confident your test data setup is exactly 10:
        assertEquals(5, memories.size());
    }

    @Test
    void shouldAdd() throws DataAccessException {
        // NOTE: This test will permanently add a record to the database unless
        // you use a transaction manager or a separate test database setup.

        // 1. Create a new Memory object (user_id 5 = Ethan)
        Memory newMemory = new Memory();
        newMemory.setUserID(5); // Must use a valid user_id
        newMemory.setContent("Just added a new test memory.");
        newMemory.setShareable(false);
        newMemory.setFrom("Ethan");

        // The service layer might return a Result object, but for integration
        // we assume the data access part works and test if the object is returned
        // with a new ID. (Assuming MemoryService returns the added Memory)
        service.add(newMemory);
        Memory addedMemory = service.findMemoryById(11);
        // 2. Assert the ADD operation succeeded
        assertNotNull(addedMemory);
        assertTrue(addedMemory.getId() > 10, "New ID should be greater than the max existing ID (10)");
        assertEquals("Just added a new test memory.", addedMemory.getContent());

    }

    @Test
    void shouldUpdate() throws DataAccessException {
        // 1. Get an existing memory to update (e.g., memory_id 6, Charlie)
        Memory existingMemory = service.findMemoryById(6);
        assertNotNull(existingMemory);

        // 2. Modify the properties
        existingMemory.setContent("UPDATED: Landed the big client contract and celebrated.");
        existingMemory.setShareable(true); // Change from 0 (private) to 1 (public)

        // 3. Perform the update
        boolean success = service.update(existingMemory).isSuccess();

        assertTrue(success, "Update should return true for a successful modification.");

        // 4. Verify the update by fetching the memory again
        Memory updatedMemory = service.findMemoryById(6);
        assertNotNull(updatedMemory);
        assertEquals(existingMemory.getContent(), updatedMemory.getContent());
        assertTrue(updatedMemory.isShareable()); // Check if the change was saved

        // Test update failure (non-existent ID)
        Memory badMemory = new Memory();
        badMemory.setId(1000);
        badMemory.setContent("Should fail");
        assertFalse(service.update(badMemory).isSuccess(), "Update should fail for a non-existent ID.");
    }

    @Test
    void shouldDeleteById() throws DataAccessException {
        // NOTE: Deletion is highly destructive in integration tests.
        // It's best to delete a record you just added, or rely on the transaction manager.

        // 1. Test a successful delete (e.g., memory_id = 9, Ethan)
        boolean success = service.deleteById(9).isSuccess();
        assertTrue(success, "Should return true for a successful deletion.");

        // 2. Verify the deletion by attempting to find the memory
        Memory deletedMemory = service.findMemoryById(9);
        assertNull(deletedMemory, "Deleted memory should no longer be found.");

        // 3. Test a failing delete (non-existent ID)
        assertFalse(service.deleteById(1000).isSuccess(), "Delete should return false for a non-existent ID.");
    }
}