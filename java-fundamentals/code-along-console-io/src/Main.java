import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("What is your name:");
        String name = console.nextLine();
        System.out.println("What is your quest");
        String quest = console.nextLine();
        int airSpeed = 0;

        boolean isValid = false;


        while (!isValid){
            System.out.println("What is the air speed of swallow carrying a coconut");
            try {
                airSpeed = Integer.parseInt(console.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input. PLease Enter a Whole Number");
            }
        }

        String formattedOutput = String.format(
                """
                 ---Air Speed---
                 Swallow Airspeed: %d       
                 """, airSpeed
        );
        System.out.println(formattedOutput);


        System.out.println("Your Name is " + name );
        System.out.println("Your Quest is " + quest );
        System.out.printf("Your Air Speed is %d NAUTS", airSpeed);


    }
}
