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
            try {
                number = Integer.parseInt(getInput(prompt));
                goodInput = true;
            } catch (NumberFormatException e){
                writeMessage("Input a valid number");
            } catch(Exception e) {
                writeMessage(e.getMessage());
            }
        }
        return number;
    }

    public int getIntegerInBetween(String prompt, int min, int max) {
        boolean goodInput = false;
        int number = -1;

        while(!goodInput) {
            number = getInteger(prompt);
            if(number >= min && number <= max) {
                goodInput = true;
            }
            else {
                writeMessage("Enter a number between " + min + " and " + max);
            }
        }
        return number;
    }


    public String getInput(String prompt) {
        writeMessage(prompt);
        return myScanner.nextLine();
    }


    public String getInputWithDefault(String prompt, String defaultValue) {
        String result = getInput(prompt);
        if (result.isEmpty()){
            result = defaultValue;
        }
        return result;
    }


    public String getNonNullNonEmptyString (String prompt) {
        boolean goodInput = false;
        String result = null;

        while(!goodInput) {
            result = getInput(prompt);
            if(result.isEmpty()) {
                writeMessage("Enter a valid response.");
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
            try {
                number = Double.parseDouble(getInput("Enter the amount of cash on hand: "));
                goodInput = true;
            } catch (NumberFormatException e){
                writeMessage("Input a valid number");
            } catch(Exception e) {
                writeMessage(e.getMessage());
            }
        }
        return number;
    }

    public double getPositiveMoney() {
        boolean goodInput = false;
        double number = 0.0;

        while(!goodInput) {
                number = getMoney();
                if(number > 0.0) {
                    goodInput = true;
                } else {
                    writeMessage("Enter a positive amount of money.");
                }
        }
        return number;
    }
}
