import java.util.ArrayList;
import java.util.Collections;
public class Main {
    public static void main(String[] args) {
        //Creating an arraylist
        ArrayList<String> students = new ArrayList<>();

        //Adding 5 students to the array list
        students.add("Henok");
        students.add("Micheal");
        students.add("Jordan");
        students.add("Kelsey");
        students.add("Sampson");

        //Retrieving the 3rd student
        String student3 = students.get(2);
        //Removing the first student
        String removed = students.remove(1);
        //Checking how many students are there
        int total = students.size();
        //Checking if there are students
        boolean empty = students.isEmpty();

        //Prints out results before sorting the list
        System.out.println("Third student: " + student3);
        System.out.println("Removed student: " + removed);
        System.out.println("Total students remaining: " + total);
        System.out.println("List is empty: " + students.isEmpty());

        //Sorts the list and prints the results
        Collections.sort(students);
        System.out.println("Sorted List: "+ students);

        //Bonus Challenge
        ArrayList<String> newStudents = new ArrayList<>();

        newStudents.add("Jeff");
        newStudents.add("Tommy");
        newStudents.add("Kayla");

        students.addAll(newStudents);
        Collections.sort(students);
        System.out.println("Sorted List with New Students: "+ students);
    }
}