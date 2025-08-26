import java.util.Scanner;
public class ForLoops {
    public static void main(String[] args) {

    //1. Count Up! (For Loop)
    //● Write a program that prints numbers 1 to 100 using a for loop.
    //● Modify the program to print only even numbers.
    for (int i = 1; i <101 ; i++){
//        System.out.println(i);
        if(i % 2 == 0){
            System.out.println(i);
        }
    }
    //2.Multiplication Table (For Loop)
    //● Ask the user for a number.
    //● Print the multiplication table (1 to 10) for that number
    Scanner user = new Scanner(System.in);
    System.out.println("Enter a number");
    int mult = user.nextInt();

    for(int i = 1; i<11; i++){
        int result = mult * i;
        System.out.println(mult + " x " + i + " = " + result);
    }
    user.close();
    }
}
