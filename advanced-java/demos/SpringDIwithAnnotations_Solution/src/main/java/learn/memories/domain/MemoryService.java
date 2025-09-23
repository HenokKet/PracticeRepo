package learn.memories.domain;

import learn.memories.data.DataAccessException;
import learn.memories.data.MemoryRepository;
import learn.memories.models.Memory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryService {
    private final MemoryRepository repository;

    public MemoryService(MemoryRepository repository){
        this.repository = repository;
    }

    public List<Memory> findShareableMemories() throws DataAccessException {
        return repository.findShareable(true);
    }

    public List<Memory> findPrivateMemories() throws DataAccessException {
        return repository.findShareable(false);
    }

    public MemoryResult add(Memory memory) throws DataAccessException {
        MemoryResult result = validate(memory);
        //Additional Validators OR Overloaded Validation Method
        if (memory.getId() > 0) {
            result.addErrorMessage("Memory ID should not be set.");
        }

        if(result.isSuccess()) {
            memory = repository.add(memory);
            result.setMemory(memory);
        }

        return result;

    }

    public MemoryResult update(Memory memory) throws DataAccessException {
        MemoryResult result = validate(memory);

        //Additional Validation
        if (memory.getId() <=0){
            result.addErrorMessage("Memory Id is Required");
        }

        if(result.isSuccess()){
            if(repository.update(memory)){
                result.setMemory(memory);
            } else {
                result.addErrorMessage("Memory Id " + memory.getId() + " was not found.");
            }
        }
        return result;
    }

    public MemoryResult deleteById(int memoryId) throws DataAccessException {
        MemoryResult result = new MemoryResult();
        Memory memoryToDelete = repository.findById(memoryId);

        if (!repository.deleteById(memoryId)) {
            result.addErrorMessage("Memory Id " + memoryId+ " was not found.");
        } else {
            result.setMemory(memoryToDelete);
        }

        return result;
    }

    private MemoryResult validate(Memory memory){
        MemoryResult memoryResult = new MemoryResult();

        if(memory == null){
            memoryResult.addErrorMessage("Memory Can't Be Null");
            return memoryResult;
        }

        if (memory.getFrom() == null || memory.getFrom().isEmpty()){
            memoryResult.addErrorMessage("Memory From is required");
        }

        if (memory.getContent()== null || memory.getContent().isEmpty()){
            memoryResult.addErrorMessage("Memory Content is required");
        }

        return memoryResult;

    }



}
