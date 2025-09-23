package learn.memories.ui;
import learn.memories.models.Memory;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class View {
    private final TextIO io;

    public View(TextIO io){
        this.io = io;
    }
    //AO: This should be called Main Menu
    public int chooseMenuOption() {
        displayHeader("Main Menu");
        io.printLn("0. Exit");
        io.printLn("1. View Memories");
        io.printLn("2. Add a Memory");
        io.printLn("3. Update a Memory");
        io.printLn("4. Delete a Memory");
        return io.readInt("Choose a numeric option [1-4]", 0, 4);
    }

    public Memory chooseMemory(List<Memory> memories){
        displayMemories(memories);
        Memory result = null;
        if(memories.size() > 0) {
            int memoryId = io.readInt("Choose a Memory ID: ");
            for (Memory memory : memories){
                if(memory.getId() == memoryId){
                    result = memory;
                    break;
                }
            }
        }
        return result;
    }

    public Memory createMemory() {
        displayHeader("Add a Memory");
        Memory result = new Memory();
        result.setFrom(io.readString("From: "));
        result.setContent(io.readString("Content: "));
        result.setShareable(io.readBoolean("Shareable [y/n]: "));
        return result;
    }

    public Memory editMemory(Memory memory){
        displayHeader("Update");
        String from = io.readString("From (" + memory.getFrom() + ") :");
        if(!from.trim().isEmpty()){
            memory.setFrom(from);
        }

        String content = io.readString("Content (" + memory.getContent() + ") :");
        if(!content.trim().isEmpty()){
            memory.setContent(content);
        }

        String shareable = io.readString("Shareable (" + (memory.isShareable()
                ? "y" : "n") + ") [y/n]: ");
        if(!shareable.trim().isEmpty()){
            memory.setShareable(shareable.equalsIgnoreCase("y"));
        }

        return memory;

    }

    //Utility Methods
    public void displayHeader(String message) {
        int length = message.length();
        io.printLn("*".repeat(length));
        io.printLn(message);
        io.printLn("*".repeat(length));
    }

    public void displayMemories(List<Memory> memories){
        if(memories.isEmpty()){
            displayHeader("No Memories Found");
        } else {
            displayHeader("Memories");
            for(Memory memory: memories){
                io.printf("ID: %s, From: %s%n%s%n", memory.getId(), memory.getFrom(),
                        memory.getContent());
            }
        }
    }

    public void displayErrors(List<String> errorMessages){
        displayHeader("Errors:");
        for(String errorMessage: errorMessages){
            io.printLn(errorMessage);
        }
    }

    public boolean isPublic(){
        io.printLn("1. Public");
        io.printLn("2. Private");
        return io.readInt("Choose [1-2]: ", 1,2) == 1;
    }

    public void displayMessage(String message){
        io.printLn("");
        io.printLn(message);
    }
}
