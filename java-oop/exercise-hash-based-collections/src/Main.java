import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Created a new HashMap for the months
        HashMap<Integer, String> months = new HashMap<>();

        //Inserted the values for each month
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");

        //For Loop which displays each month and corresponding ID
        for(Integer key : months.keySet()) {
            System.out.println("Month #"+ key + " is " + months.get(key));
        }

        //Created a new HashMap for the deck of cards
        HashMap<String, List<String>> deck =  new HashMap<>();
        //List of cards for each row
        List<String> cards = Arrays.asList("Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King");

        //Inserted cards into each suit
        deck.put("Hearts", new ArrayList<>(cards));
        deck.put("Diamonds", new ArrayList<>(cards));
        deck.put("Clubs", new ArrayList<>(cards));
        deck.put("Spades", new ArrayList<>(cards));

        //Nested For Loop to print out the full deck
        System.out.println("\nFull deck:");
        for (String suit : deck.keySet()) {
            for (String rank : deck.get(suit)) {
                System.out.println(rank + " of " + suit);
            }
        }
    }
}