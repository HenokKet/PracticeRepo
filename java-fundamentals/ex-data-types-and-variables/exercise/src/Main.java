//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Exercise Code Starts Here:");

        //Henok Ketema

        //Sports Statistics
        //1 Declare and assign the following variables related to a soccer player:
        //2 playerName → store the name of a soccer player.
        String playerName = "Mark Messi";
        //3 jerseyNumber → store the player's jersey number.
        int jerseyNumber = 11;
        //4 position → store the player’s field position (e.g., "Goalkeeper").
        String position = "Striker";
        //5 isStarter → store whether the player is a starter (true or false).
        boolean isStarter = true;
        //6 teamName → store the name of the player’s team.
        String teamName= "Liverpool";


        //Movie Information
        //1 Declare and assign the following variables for a movie:
        //2 movieTitle → store the title of a movie.
        String movieTitle = "Black panther";
        //3 releaseYear → store the year the movie was released.
        int releaseYear = 2015;
        //4 rating → store the rating (e.g., "PG-13").
        String rating = "PG-13";
        //5 isSequel → store whether the movie has a sequel (true or false).
        boolean isSequel = false;
        //6 leadActor → store the name of the lead actor.
        String leadActor = "Chadwick Bowman";


        //Weather Report
        //1. Declare and assign the following variables for a weather forecast:
        //2. cityName → store the name of a city.
        String cityName = "Milwaukee";
        //3. temperature → store the temperature in Fahrenheit.
        int temperature = 74;
        //4. isRaining → store whether it is currently raining (true or false).
        boolean isRaining = false;
        //5. humidity → store the humidity percentage.
        int humidity = 76;
        //6. weatherCondition → store a short description of the weather (e.g. "Cloudy").
        String weatherCondition = "Cloudy with strong winds";



        //Flight Information
        //1 Declare and assign the following variables for a flight at an airport:
        //2 flightNumber → store the flight number (e.g., "AA256").
        String flightNumber = "UA245";
        //3 departureCity → store the departure city (e.g., "New York").
        String departureCity = "Dallas";
        //4 arrivalCity → store the arrival city (e.g., "Los Angeles").
        String arrivalCity = "Las Vegas";
        //5 gateNumber → store the gate number for the flight.
        int gateNumber = 12;
        //6 terminal → store the terminal number at the airport.
        int terminal = 2;
        //7 isDelayed → store whether the flight is delayed (true or false).
        boolean isDelayed = true;



        //Part 2: Printing Variables
        // Use System.out.println() to print each set of variables.
        // Format the output to display meaningful sentences
        System.out.println("Player Name = " + playerName);
        System.out.println("Starting " + position + " for " + teamName + " number " + jerseyNumber);
        System.out.println("One of my favorites from " + releaseYear + " is the movie "+ movieTitle +" starred by "+ leadActor );
        System.out.println("The movie is rated " + rating);
        System.out.println("The weather today for "+ cityName + " is: "+ temperature + " degrees F with "+ humidity+" percent humidity" );
        System.out.println("The weather conditions today are" + weatherCondition );
        System.out.println("My flight number " + flightNumber );
        System.out.println("I am flying from " + departureCity + " to " + arrivalCity );
        System.out.println("My terminal is " + terminal + " at gate "+ gateNumber );
        System.out.println("Is my flight delayed? " + isDelayed );
    }
}