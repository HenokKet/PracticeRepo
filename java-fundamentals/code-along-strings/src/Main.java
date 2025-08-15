//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String example = "This is an example of a string";
        char letter = 'A';

        String partOne;
        String partTwo;

        partOne = "Jack and Jill";
        partTwo = "Went up the hill";

        System.out.println(partOne + " " + partTwo);

        String color = "red";
        System.out.println("The color is " + color.length() + " Characters Long");

        for (int i = 0; i<color.length(); i++){
            System.out.println(color.charAt(i));
        }

        System.out.println(partTwo.contains("he"));

        String searchThis = "The quick brown fox jumped over the lazy dog";
        System.out.println(searchThis.toLowerCase().indexOf('t'));
        System.out.println(searchThis.toLowerCase().indexOf("quick"));
        System.out.println(searchThis.toLowerCase().indexOf("sword"));

        System.out.println(searchThis.substring(0,3));
        int start = searchThis.indexOf("dog");

        System.out.println(searchThis.substring(start));

        String chloeSearch = searchThis.replace("dog", "cat");
        System.out.println(chloeSearch);

        String underscoredVersion = "This_text_needs_spaces_in_it";
        System.out.println(underscoredVersion.replace('_', ' ').replace("needs", "has"));


    }
}