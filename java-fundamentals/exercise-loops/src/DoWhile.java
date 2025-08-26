import java.util.Scanner;
public class DoWhile {
    public static void main(String[] args) {
        //Guess the Number (Do-While Loop)
        //● Generate a random number between 1 and 50.
        //● Ask the user to guess the number.
        //● Keep asking until the user gets it right using a do-while loop.
        Scanner input = new Scanner(System.in);
        int rand = (int)(Math.random() * 50) + 1;
        int guess = 0;
        System.out.println(rand);
        do{
            System.out.println("Enter your guess");
            guess = input.nextInt();
        }while (rand != guess);
        System.out.println("Congrats you guessed the number!!");

//        Simple ATM System (Do-While Loop)
//        ● Start with an account balance of $500.
//        ● Ask the user if they want to:
//        ○ 1 Withdraw
//        ○ 2 Deposit
//        ○ 3 Check Balance
//        ○ 4 Exit
//        ● Use a do-while loop to keep asking until they choose to exit
        input.nextLine();
        int account = 500;
        int choice = 0;
        boolean exit = false ;

        do{
            System.out.println("Welcome to the ATM");
            System.out.println("1 Withdraw");
            System.out.println("2 Deposit");
            System.out.println("3 Check Balance");
            System.out.println("4 Exit");
            System.out.println("Enter your choice");
            choice = input.nextInt();
            switch (choice) {
                case 1: // Withdraw
                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount = input.nextInt();
                    if (withdrawAmount > account) {
                        System.out.println("Insufficient funds!");
                    } else if (withdrawAmount <= 0) {
                        System.out.println("Invalid amount.");
                    } else {
                        account -= withdrawAmount;
                        System.out.println("Withdrawal successful. New balance: $" + account);
                    }
                    break;
                case 2: // Deposit
                    System.out.print("Enter amount to deposit: ");
                    int depositAmount = input.nextInt();
                    if (depositAmount <= 0) {
                        System.out.println("Invalid amount.");
                    } else {
                        account += depositAmount;
                        System.out.println("Deposit successful. New balance: $" + account);
                    }
                    break;
                case 3: // Check Balance
                    System.out.println("Your balance is: $" + account);
                    break;
                case 4: // Exit
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }while (!exit);
        input.close();
    }
}
