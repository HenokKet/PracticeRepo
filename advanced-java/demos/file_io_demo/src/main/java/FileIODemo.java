import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileIODemo {
    public static void main(String[] args) {
        File file = new File("colors.txt");
        //File file = new File("C:\\Users\\orlan\\OneDrive\\colors.txt");
        //Test File to ensure it's a file
        if (file.isDirectory()){
            System.out.println("Colors File Parameter is a directory. Cannot write.");
            System.exit(1);
        }
        //Step 1: Create the file
        try {
            if (file.createNewFile()){
                System.out.println("Color File Created");
            } else {
                System.out.println("Color File Already Exists.");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

//        //Step 2: Write Colors to the file
//        try (PrintWriter writer = new PrintWriter(file)) {
//            writer.println("red");
//            writer.println("blue");
//            writer.println("green");
//        } catch (FileNotFoundException e){
//            e.printStackTrace();
//        }

        //Step 3: Append to a file
        try (FileWriter fileWriter = new FileWriter("colors.txt", true);
                PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println("red");
            writer.println("green");
            writer.println("blue");
            writer.println("yellow");
            writer.println("orange");
            writer.println("purple");
        } catch (IOException e){
            e.printStackTrace();
        }

        //Step4: Read from file
        try (FileReader fileReader = new FileReader("colors.txt");
        BufferedReader reader = new BufferedReader(fileReader)){
            for (String line = reader.readLine(); line != null; line = reader.readLine()){
                System.out.println(line);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        boolean success = file.delete();
        if (success) {
            System.out.println("Colors File was deleted.");
        } else {
            System.out.println("Unable to delete colors file");
        }

        //NIO Class - Writing to file
        ArrayList<String> lines = new ArrayList<>();
        lines.add("hydrogen");
        lines.add("helium");
        lines.add("lithium");

        Path path = Paths.get("elements.txt");
        try {
            Files.write(path, lines, StandardOpenOption.CREATE);
        } catch (IOException e){
            e.printStackTrace();
        }

        //Append to NIO
        lines.clear();
        lines.add("beryllium");
        lines.add("boron");
        lines.add("borophyll");

        try {
            Files.write(path, lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read the File back to the console
        try {
            for(String line: Files.readAllLines(path)){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //delete the file
        try{
            success = Files.deleteIfExists(path);
            if(success){
                System.out.println("Elements File Deleted");
            } else {
                System.out.println("Elements was not deleted");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Reaad CSV File
        path = Paths.get("test_data.csv");
        String elements[];
        List<Student> students = new ArrayList<>();

        try {
            for(String line: Files.readAllLines(path)){
                elements = line.split(",");
                Student student = new Student();
                student.setName(elements[0]);
                student.setRole(elements[1]);
                student.setFavoriteColor(elements[2]);
                students.add(student);
            }

            for(Student student: students){
                System.out.println(student.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
