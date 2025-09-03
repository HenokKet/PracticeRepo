public class GuessingGame {
    public static void main(String[] args) {

        ConsoleIO myIO = new ConsoleIO();
        String name, favoriteColor, favoriteAnimal;
        int favoriteNumber;
        double cash;

        myIO.writeMessage("Welcome to my guessing game");

        myIO.writeMessage("Please answer a series of questions.");

        name = myIO.getInput("What is your name?");

        if (name == null || name.length() == 0) {
            myIO.writeMessage("It's okay if you don't want to tell me your name.");
            name = "Nobody";
        }

        favoriteColor = myIO.getInput("What's your favorite color?");

        favoriteNumber = myIO.getInteger("What's your favorite number?");

        if (favoriteNumber < 1111 || favoriteNumber > 9999) {
            favoriteNumber =
                    myIO.getIntegerInBetween(
                            "I mean what is your favorite number between 1111 and 9999"
                            , 1111, 9999);
        }

        favoriteAnimal = myIO.getNonNullNonEmptyString("What is your favorite animal?");

        cash = myIO.getMoney();

        myIO.writeMessage("Thanks for playing my game!");
        myIO.writeMessage("Your name is: " + name);
        myIO.writeMessage("Your favorite color is: " + favoriteColor);
        myIO.writeMessage("Your favorite animal is: " + favoriteAnimal);
        myIO.writeMessage("Your cash on hand is :" + cash);
        myIO.writeMessage("Finally my guess at your bank pin is :" + favoriteNumber);


    }
}
