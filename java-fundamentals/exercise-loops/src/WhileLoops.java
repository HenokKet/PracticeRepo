import java.util.Scanner;
public class WhileLoops {
    public static void main(String[] args) {

        //1. Countdown Timer (While Loop)
        //● Prompt the user for a starting number.
        //● Use a while loop to count down to zero.
        //● Print "Blast off!" when it reaches zero
        Scanner count = new Scanner(System.in);
        System.out.println("Enter a countdown");
        int countdown = count.nextInt();
        while (countdown !=0){
            System.out.println(countdown);
            countdown--;
        }
        System.out.println("Blast off!!");
        //2. Password Validator (While Loop)
        //● Ask the user to enter a password.
        //● Keep asking until they enter the correct password ("letmein").
        String password ="letmein";
        String user = count.nextLine();
        while (!password.equals(user)){
            System.out.println("Enter a password");
            user = count.nextLine();
        }
        System.out.println("You cracked the password!!");
        count.close();
    }
}
