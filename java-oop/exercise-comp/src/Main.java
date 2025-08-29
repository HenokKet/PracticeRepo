import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //Starter Variables including console and artifact info
        Scanner console = new Scanner(System.in);
        String userArtifact,firstName,lastName,career;
        int year = 0;
        //Welcome Screen
        System.out.println("Hello and welcome!");
        //Prompt for artifact name
        System.out.print("Enter the name of the new Artifact: ");
        userArtifact = console.nextLine();
        //Prompt for artifact year
        boolean valid = false;

        while (!valid) {
            System.out.print("Enter the year of discovery: ");
            try {
                year = console.nextInt();
                console.nextLine(); // consume newline
                valid = true;       // exit loop once input is valid
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                console.nextLine(); // clear bad input
            }
        }
        //Prompt for discoverer first and last name
        System.out.print("Enter the first name of the discoverer: ");
        firstName = console.nextLine();
        System.out.print("Enter the last name of the discoverer: ");
        lastName = console.nextLine();
        //Prompt for discoverer profession
        System.out.print("Enter the profession of the discoverer: ");
        career = console.nextLine();
        //Intialize the discoverer with the user input
        Person discoverer = new Person(firstName, lastName, career);

        //Prompt for if discoverer and curator are the same person
        System.out.print("is the discoverer also the curator?(y/n): ");
        //Empty curator variable which will be filled in after if statement
        Person curator;
        //if statement based off of user input
        if (console.nextLine().equalsIgnoreCase("y")){
            curator = discoverer;
        }else{
            System.out.print("Enter the first name of the curator: ");
            String cfirstName = console.nextLine();
            System.out.print("Enter the last name of the curator: ");
            String clastName = console.nextLine();
            System.out.print("Enter the profession of the curator: ");
            String ccareer = console.nextLine();
            curator = new Person(cfirstName, clastName, ccareer);
        }
        //creates an artifact using the user input and displays the result
        Artifact artifact = new Artifact(userArtifact, year, discoverer, curator);
        System.out.println("\nArtifact Summary ");
        System.out.println(artifact.toString());
    }
}