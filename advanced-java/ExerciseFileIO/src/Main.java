import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("student_data.txt");
        try {
            // Step 1: Create file
            if (file.createNewFile()) {
                System.out.println("Student data file successfully created.");
            } else {
                System.out.println("Student data file already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Step 2: Write data
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
            {
                writer.println("Alice, A");
                writer.println("Bob, B");
                writer.println("Charlie, A+");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Step 3: Append to a file
        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println("David, B+");
            writer.println("Eva, A");
        } catch (IOException e){
            e.printStackTrace();
        }

        //Step4: Read from file
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)){
            for (String line = reader.readLine(); line != null; line = reader.readLine()){
                System.out.println(line);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        // Step 5: Path Exploration
        System.out.println("\nPath Exploration: ");
        // File paths
        System.out.println("Relative path: " + file.getPath());        // relative path from the project
        System.out.println("Absolute path: " + file.getAbsolutePath()); // full path from root

        //Step 6: Delete the file
        boolean success = file.delete();
        if (success) {
            System.out.println("Student data File was deleted.");
        } else {
            System.out.println("Unable to delete Student data file");
        }

        /*
        Step 7 Question Answers
        1. relative path pertains to the project instead of the entire computer which is more efficient and simpler.
        2. the file will never close and can lead to issues such as file corruption.
        3. when adding new students to a school for example or when you are registering a new car at a parking facility.
        4. To track employee activity such as clock in and clock out times
        */
    }
}