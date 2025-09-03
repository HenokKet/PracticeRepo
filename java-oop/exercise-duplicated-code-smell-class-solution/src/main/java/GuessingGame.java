public class GuessingGame {
    public static void main(String[] args) {

        ConsoleIO myIO = new ConsoleIO();

        myIO.writeMessage("Welcome to my guessing game");

        myIO.writeMessage("Please answer a series of questions.");

        String name = myIO.getInputWithDefault("What is your name? (It's okay if you don't want to tell me your name. Just Press Enter to Skip)", "Nobody");

        String favoriteColor = myIO.getNonNullNonEmptyString("What's your favorite color?");

        int favoriteNumber = myIO.getIntegerInBetween("What's your favorite number (Between 1111 and 9999)", 1111, 9999);

        String favoriteAnimal = myIO.getNonNullNonEmptyString("What is your favorite animal?");

        double cash = myIO.getPositiveMoney();

        myIO.writeMessage("Thanks for playing my game!");
        myIO.writeMessage("Your name is: " + name);
        myIO.writeMessage("Your favorite color is: " + favoriteColor);
        myIO.writeMessage("Your favorite animal is: " + favoriteAnimal);
        myIO.writeMessage("Your cash on hand is :" + cash);
        myIO.writeMessage("Finally my guess at your bank pin is :" + favoriteNumber);


    }
}
