package learn.memories;

import learn.memories.data.MemoryFileRepository;
import learn.memories.domain.MemoryService;
import learn.memories.ui.ConsoleIO;
import learn.memories.ui.Controller;
import learn.memories.ui.View;

import java.io.Console;

public class App {
    public static void main(String[] args) {
        MemoryFileRepository repository = new MemoryFileRepository("./data/memories.txt");
        MemoryService service = new MemoryService(repository);

        ConsoleIO io = new ConsoleIO();
        View view = new View(io);

        Controller controller = new Controller(view, service);

        controller.run();
    }
}
