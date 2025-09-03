import java.util.Scanner;

public class ConsoleIO {

    Scanner myScanner = new Scanner(System.in);

    public void writeMessage(String message) {
        System.out.println(message);
    }

    public int getInteger(String prompt) {
        boolean goodInput = false;
        int number = -1;

        while(!goodInput) {
            System.out.println(prompt);
            try {
                number = Integer.parseInt(myScanner.nextLine());
                goodInput = true;
            } catch(Exception e) {
                System.out.println("Input a valid number");
            }
        }
        return number;
    }

    public int getIntegerInBetween(String prompt, int min, int max) {
        boolean goodInput = false;
        int number = -1;

        while(!goodInput) {
            System.out.println(prompt);
            try {
                number = Integer.parseInt(myScanner.nextLine());
                if(number >= min && number <= max) {
                    goodInput = true;
                }
                else {
                    System.out.println("Enter a number between " + min + " and " + max);
                }
            } catch(Exception e) {
                System.out.println("Input a valid number");
            }
        }
        return number;
    }

    public String getInput(String prompt) {
        System.out.println(prompt);
        return myScanner.nextLine();
    }

    public String getNonNullNonEmptyString (String prompt) {
        boolean goodInput = false;
        String result = null;

        while(!goodInput) {
            System.out.println(prompt);
            result = myScanner.nextLine();
            if(result == null || result.length() <= 0) {
                System.out.println("Enter a valid response.");
            } else {
                goodInput = true;
            }
        }

        return result;
    }

    public double getMoney() {
        boolean goodInput = false;
        double number = 0.0;

        while(!goodInput) {
            System.out.println("Enter the amount of cash on hand: ");
            try {
                number = Double.parseDouble(myScanner.nextLine());
                goodInput = true;
            } catch(Exception e) {
                System.out.println("Input a valid number");
            }
        }
        return number;
    }

    public double getPositiveMoney() {
        boolean goodInput = false;
        double number = 0.0;

        while(!goodInput) {
            System.out.println("Enter the amount of cash on hand: ");
            try {
                number = Double.parseDouble(myScanner.nextLine());
                if(number > 0.0) {
                    goodInput = true;
                } else {
                    System.out.println("Enter a positive amount of money.");
                }
            } catch(Exception e) {
                System.out.println("Input a valid number");
            }
        }
        return number;
    }
}
