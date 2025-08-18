import java.util.Scanner;

//Henok Ketema Decision maze game
public class Decisions {
    public static void main(String[] args) {
        //scanner to receive input and boolean variable for game loop
        Scanner user = new Scanner(System.in);
        boolean playAgain = true;

        //Game loop
        while (playAgain) {
            // Game Intro
            System.out.println("You stand before the mouth of a mysterious cave.");
            System.out.print("Do you want to enter the cave? (yes/no): ");
            String start = user.nextLine();

            // First decision
            if (start.equals("no")) {
                System.out.println("You decide that caution is the better part of valor. You head home safely.");
                // continue to the play-again prompt
            } else if (!start.equals("yes")) {
                System.out.println("You hesitate too long. Night falls and you head home empty-handed.");
                // continue to the play-again prompt
            } else {
                // Inside the cave
                System.out.print("You dare on and two tunnels diverge ahead. Left or right? (left/right): ");
                String tunnel = user.nextLine();
                // If user goes left they win an artifact
                if (tunnel.equals("left")) {
                    System.out.print("You chose left! On a pedestal you see three artifacts: sword, shield, lamp. Choose one: ");
                    String artifact = user.nextLine();
                    //Switch statement to have user choose their artifact and reward
                    switch (artifact) {
                        case "sword":
                            System.out.println("You grasp the Sword of Dawn. A hidden door opens, revealing treasure!");
                            break;
                        case "shield":
                            System.out.println("You lift the Aegis Shield. The chamber rumbles; rocks seal the entrance. TRAPPED… but you find a side passage later.");
                            break;
                        case "lamp":
                            System.out.println("You light the lamp. Invisible ink reveals a secret exit and supplies.");
                            break;
                        default:
                            System.out.println("You reach for an unknown trinket. It crumbles to dust. The magic fades.");
                            break;
                    }
                    // If user goes right they encounter a monster
                } else if (tunnel.equals("right")) {
                    System.out.println("The right tunnel narrows. A monster appears out of nowhere.");
                    System.out.print("Do you choose to fight or flee? (fight/flee): ");
                    String action = user.nextLine();

                    //If user chooses to fight then it will prompt where to aim
                    if (action.equals("fight")) {
                        System.out.print("Bold choice! First strike—where do you aim? (head/legs): ");
                        String aim = user.nextLine();

                        if (aim.equals("head")) {
                            System.out.println("Bold strike! You tame the beast; it reveals a map to hidden treasure.");
                        } else if (aim.equals("legs")) {
                            System.out.println("You sweep low, but fail to do enough damage. The beast defeats you.");
                        } else {
                            System.out.println("Hesitation costs you—the phantom lands the first strike.");
                        }
                    //If user decides to run it prompts 2 tunnels to choose to escape from
                    } else if (action.equals("flee")) {
                        System.out.print("You see a lighted tunnel splitting left and right. Which way? (left/right): ");
                        String escape = user.nextLine();

                        if (escape.equals("left")) {
                            System.out.println("You find daylight—and a pouch left by past explorers.");
                        } else if (escape.equals("right")) {
                            System.out.println("You find yourself back where you started.");
                        } else {
                            System.out.println("You dart aimlessly and circle back to the entrance.");
                        }
                    } else {
                        System.out.println("Frozen between choices, the beast catches up with you and defeats you.");
                    }
                } else {
                    System.out.println("You wander into a dead end marked with ancient warnings.");
                }
            }
            // Prompts the user on if they want to play again?
            System.out.println("Would you like to play again.(yes or no)");
            String choice = user.nextLine();
            if (choice.equals("yes")) {
                playAgain = true;
            } else if (choice.equals("no")) {
                playAgain = false;
            } else {
                System.out.println("Incorrect choice Game will automatically end");
                playAgain = false;
            }
        }
        user.close();
    }
}
