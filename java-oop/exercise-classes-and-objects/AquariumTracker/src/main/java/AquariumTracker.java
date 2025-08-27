import java.util.Scanner;

public class AquariumTracker {
    public static void main(String[] args) {
        //Scanner for input and creating an instance of an fish
        Scanner in = new Scanner(System.in);
        AquariumFish fish = new AquariumFish();

        //Welcome screen
        System.out.println("Enter the information for your fish.");

        //Gets the user input and sets the fish species
        System.out.print("Species Name: ");
        fish.setSpecies(in.nextLine());

        //Gets the user input and sets the fish common name
        System.out.print("Common Name: ");
        fish.setCommonName(in.nextLine());

        //Gets the user input and sets the max temp
        System.out.print("Maximum Temperature: ");
        //while loop to prevent nonnumerical output
        while (!in.hasNextDouble()) {
            System.out.print("Please Enter a number : ");
            in.next();
        }
        double max = in.nextDouble();
        fish.setMaxTemp(max);
        in.nextLine();

        //Gets the user input and sets the min temp
        System.out.print("Minimum Temperature: ");
        double min =0;
        //while loop to prevent nonnumerical output
        while (!in.hasNextDouble()) {
            System.out.print("Please Enter a number : ");
            in.next();
        }
        min = in.nextDouble();
        fish.setMinTemp(min);
        in.nextLine();

        //Gets the user input and sets the fish diet
        System.out.print("Diet: ");
        fish.setDiet(in.nextLine());

        //Output the fish data
        System.out.println("\n--- Fish Summary ---");
        System.out.println(fish.toString());
    }
}
