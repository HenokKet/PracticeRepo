package learn.memories.domain;

import learn.memories.models.Memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryResult {
    private List<String> messages = new ArrayList<>();
    private Memory memory;

    public List<String> getErrorMessages() {
        return messages;
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.isEmpty();
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory){
        this.memory = memory;
    }
}
