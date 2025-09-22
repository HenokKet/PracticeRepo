package learn.unexplained.ui;

import com.sun.net.httpserver.Headers;
import learn.unexplained.domain.EncounterResult;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner console = new Scanner(System.in);

    public MenuOption displayMenuGetOption() {
        printHeader("Main Menu");

        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%s. %s%n", i + 1, options[i].getMessage());
        }

        String msg = String.format("Select [%s-%s]:", 1, options.length);
        int value = readInt(msg, 1, options.length);
        return options[value - 1];
    }

    public void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public void printAllEncounters(List<Encounter> encounters) {
        printHeader(MenuOption.DISPLAY_ALL.getMessage());
        if (encounters == null || encounters.size() == 0) {
            System.out.println();
            System.out.println("No encounters found.");
        } else {
            for (Encounter e : encounters) {
                System.out.printf("%s. Type:%s, Occurrences:%s, When:%s, Desc:%s%n",
                        e.getEncounterId(),
                        e.getType(),
                        e.getOccurrences(),
                        e.getWhen(),
                        e.getDescription());
            }
        }
    }
    //Uses same logic in printaAllEncounters but just printing 1
    public void printEncounters(String header, List<Encounter> encounters) {
        printHeader(header);
        if (encounters == null || encounters.isEmpty()) {
            System.out.println();
            System.out.println("No encounters found.");
        } else {
            for (Encounter e : encounters) {
                System.out.printf("%s. Type:%s, Occurrences:%s, When:%s, Desc:%s%n",
                        e.getEncounterId(),
                        e.getType(),
                        e.getOccurrences(),
                        e.getWhen(),
                        e.getDescription());
            }
        }
    }

    public void printResult(EncounterResult result) {
        if (result.isSuccess()) {
            if (result.getPayload() != null) {
                System.out.printf("Encounter Id %s .%n", result.getPayload().getEncounterId());
            }
        } else {
            printHeader("Errors");
            for (String msg : result.getMessages()) {
                System.out.printf("- %s%n", msg);
            }
        }
    }
    //just used for delete
    public void printDeleteResult(boolean success, int id) {
        if (success) {
            System.out.printf("Encounter Id %s deleted.%n", id);
        } else {
            System.out.printf("Encounter Id %s not found.%n", id);
        }
    }
    //public method used to print in controller
    public void info(String msg) {
        System.out.println(msg);
    }

    public Encounter makeEncounter() {
        printHeader(MenuOption.ADD.getMessage());
        Encounter encounter = new Encounter();
        encounter.setType(readType());
        encounter.setOccurrences(readInt("Number of occurrences:"));
        encounter.setWhen(readRequiredString("When:"));
        encounter.setDescription(readRequiredString("Description:"));
        return encounter;
    }

    public Encounter editEncounter(Encounter original) {
        printHeader("Edit Encounter");
        //Uses same logic in addEncounter to edit encounter without readding it
        String when = readRequiredString("When (" + original.getWhen() + "): ");
        if (!when.isBlank()) original.setWhen(when);

        String desc = readRequiredString("Description (" + original.getDescription() + "): ");
        if (!desc.isBlank()) original.setDescription(desc);

        String occStr = readRequiredString("Occurrences (" + original.getOccurrences() + "): ");
        if (!occStr.isBlank()) {
            try {
                int occ = Integer.parseInt(occStr.trim());
                if (occ > 0) original.setOccurrences(occ);
                else System.out.println("Occurrences must be greater than 0. Keeping previous value.");
            } catch (NumberFormatException ex) {
                System.out.println("Not a valid number. Keeping previous value.");
            }
        }

        String changeType = readRequiredString("Change type? (y/N): ").trim().toLowerCase();
        if (changeType.startsWith("y")) {
            original.setType(promptEncounterType());
        }

        return original;
    }

    private String readString(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    private String readRequiredString(String message) {
        String result;
        do {
            result = readString(message);
            if (result.trim().length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.trim().length() == 0);
        return result;
    }

    //Made this public so I can use it in Controller
    public int readInt(String message) {
        String input = null;
        int result = 0;
        boolean isValid = false;
        do {
            try {
                input = readRequiredString(message);
                result = Integer.parseInt(input);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("%s is not a valid number.%n", input);
            }
        } while (!isValid);

        return result;
    }

    private int readInt(String message, int min, int max) {
        int result;
        do {
            result = readInt(message);
            if (result < min || result > max) {
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        } while (result < min || result > max);
        return result;
    }

    private EncounterType readType() {
        int index = 1;
        //added this print line for readability purposes
        System.out.println("Select A Type");
        for (EncounterType type : EncounterType.values()) {
            System.out.printf("%s. %s%n", index++, type);
        }
        index--;
        String msg = String.format("Select Encounter Type [1-%s]:", index);
        return EncounterType.values()[readInt(msg, 1, index) - 1];
    }
    //public class which is used to call readtype in controller
    public EncounterType promptEncounterType() {
        return readType();
    }
}
