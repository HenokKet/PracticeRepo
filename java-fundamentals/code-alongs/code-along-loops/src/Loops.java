import java.util.Scanner;

public class Loops {
    public static void main(String[] args) {
        System.out.println("While Loop!");

        int lines = 1;
        //Notional File
        boolean endOfFile = false;
        while (!endOfFile){
            System.out.println("Processing File Line");
            lines++;
            if (lines >= 6){
                endOfFile = true;
            }
        }

        System.out.println("\nDo While Loop");
        boolean gameOver = false;
        int health = 3;
        boolean escaped = false;
        do {
            health--;
            System.out.println("You have been attacked for 1 Damage!");
            System.out.println("Current Health = " + health);
            if (health <=0){
                gameOver = true;
                System.out.println("You have died!");
            }
            if (health == 1){
                gameOver = true;
                System.out.println("You have escaped! Congratuations!");
            }

        } while (!gameOver);


        System.out.println("Break Conditions!!!");
        System.out.println("Enter your favorite hobbies. Enter ! to quit");
        Scanner console = new Scanner(System.in);
        String input = "";
        while (!input.equals("!")) {
            System.out.println("Enter Hobby:");
            input = console.nextLine();
            System.out.println(input + " Entered");
            if (input.equals("DND")) {
                System.out.println("You found the secret hobby! Welcome to the dungeon!");

                System.out.println("Which Edition is your favorite?");
                input = console.nextLine();


                if (input.equals("REDBOX")){
                    System.out.println("You're So Cool");
                } else {
                    System.out.println("Congrats");
                }


                break;
            }
        }


        String topic = "For Loops";
        System.out.println(topic);
        for (int i = 0; i < topic.length(); i++){
            System.out.println(topic.charAt(i));
        }

        //Count to 10 By 2s
        for (int i = 0; i <=10; i+=2){
            System.out.println(i);
        }

        //With Break Statement
        for (int i = 10; i >=0; i-=2){
            System.out.println(i);
            if (i == 6){
                break;
            }
        }

        while (true){
            if (input.equals("DND")) {
                System.out.println("COOL");
                break;
            }
            System.out.println("More Hobbies NOW!");
            input = console.nextLine();
            if (input.equals("!")){
                System.out.println("Meh");
                continue;
            }
            System.out.println(input);
        }

        for (int i = 0; i<topic.length(); i++){
            if (topic.charAt(i) == 'F'){
                continue;
            }
            if (topic.charAt(i) == 'p'){
                break;
            }
            System.out.println(topic.charAt(i));


        }

        System.out.println("Loops are done!");
    }
}
