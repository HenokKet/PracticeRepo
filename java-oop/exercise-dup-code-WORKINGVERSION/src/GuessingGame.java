public class GuessingGame {
    public static void main(String[] args) {
        ConsoleIO myIO = new ConsoleIO();
        //Intro Messages
        myIO.writeMessage("Welcome to my guessing game");
        myIO.writeMessage("Please answer a series of questions.");
        //Prompts for Name
        String name = myIO.getInputWithDefault("What is your name? Press Enter if you dont want to tell me","");
        //Prompts for favorite color
        String favoriteColor = myIO.getNonNullNonEmptyString("What's your favorite color?");
        //Prompts for favorite number
        int favoriteNumber = myIO.getIntegerInBetween("What's your favorite number?",1111,9999);
        //Prompts for favorite animal
        String favoriteAnimal = myIO.getNonNullNonEmptyString("What is your favorite animal?");
        //Prompts for cash
        double cash = myIO.getPositiveMoney();
        //Displays output based off user input
        myIO.writeMessage("Thanks for playing my game!");
        myIO.writeMessage("Your name is: " + name);
        myIO.writeMessage("Your favorite color is: " + favoriteColor);
        myIO.writeMessage("Your favorite animal is: " + favoriteAnimal);
        myIO.writeMessage("Your cash on hand is : $" + cash);
        myIO.writeMessage("Finally my guess at your bank pin is : " + favoriteNumber);
    }
}
