package learn.memories.ui;

import learn.memories.data.DataAccessException;
import learn.memories.domain.MemoryResult;
import learn.memories.domain.MemoryService;
import learn.memories.models.Memory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Controller {
    private final View view;

    @Autowired
    private final MemoryService service;

    @Autowired
    public Controller(View view, MemoryService service){
        this.view = view;
        this.service = service;
    }

    public void run() {
        view.displayHeader("Welcome to Memories");
        try {
            runApp();
        } catch (DataAccessException e){
            view.displayErrors(List.of(e.getMessage()));
        }
        view.displayHeader("Goodbye");
    }

    public void runApp() throws DataAccessException {
        for (int option = view.chooseMenuOption();
             option>0;
             option = view.chooseMenuOption()) {
            switch (option) {
                case 1:
                        viewMemories();
                        break;
                case 2:
                        addMemory();
                        break;
                case 3:
                        updateMemory();
                        break;
                case 4:
                        deleteMemory();
                        break;
            }
        }

    }

    public void viewMemories() throws DataAccessException {
        List<Memory> memories = getMemories("View Memories");
        view.displayMemories(memories);
    }

    public void addMemory() throws DataAccessException {
        Memory memory = view.createMemory();
        MemoryResult result = service.add(memory);
        if(result.isSuccess()) {
            view.displayMessage("Memory " + result.getMemory().getId() + " created.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    public void updateMemory() throws DataAccessException {
        List<Memory> memories = getMemories("Update Memory");
        Memory memory = view.chooseMemory(memories);
        if (memory == null){
            view.displayMessage("Memory Not Found");
            return;
        }
        memory = view.editMemory(memory);
        MemoryResult result = service.update(memory);
        if(result.isSuccess()) {
            view.displayMessage("Memory " + result.getMemory().getId() + " updated.");
        } else {
            view.displayErrors(result.getErrorMessages());
        }
    }

    public void deleteMemory() throws DataAccessException {
        List<Memory> memories = getMemories("Delete Memory");
        Memory memory = view.chooseMemory(memories);
        if (memory != null && service.deleteById(memory.getId()).isSuccess()) {
            view.displayMessage("Memory " + memory.getId() + " deleted.");
        } else {
            view.displayMessage("Memory not found.");
        }
    }

    //Helper Methods:
    private  List<Memory> getMemories(String title) throws DataAccessException {
        view.displayHeader(title);
        if(view.isPublic()){
            return service.findShareableMemories();
        }
        return service.findPrivateMemories();

    }
}
