package org.example;

public class DebuggingExercise {

    public static void main(String[] args) {
        System.out.println("Starting Debugging Exercise...");

        int[] numbers = {5, 10, 15, 20, 25};

        System.out.println("Calculating Sum...");
        int total = calculateSum(numbers);
        System.out.println("Sum: " + total);

        System.out.println("Calculating Factorial...");
        int factorialResult = factorial(5);
        System.out.println("Factorial of 5: " + factorialResult);

        System.out.println("Debugging Exercise Complete!");
    }

    // Method to calculate sum of an array (contains a bug)
    public static int calculateSum(int[] nums) {
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) { // Bug: Incorrect loop condition
            sum += nums[i]; // Bug: Incorrect index usage
        }
        return sum;
    }

    // Recursive method to calculate factorial
    public static int factorial(int num) {
        if (num == 0) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }
}
