import java.util.Scanner;

public class App {
    static Scanner console = new Scanner(System.in);
    public static void main(String[] args) {
        String lockerId = "";
        String contents = "";
        LockerManager lockerManager = new LockerManager();
        while (true){
            switch (getMenuOptionsFromUser()) {
                case 1:
                    //Add a Locker
                    lockerId = promptUserForLockerID();
                    lockerManager.addLocker(lockerId);
                    break;
                case 2:
                    //Store Item
                    lockerId = promptUserForLockerID();
                    contents = promptUserForItem(lockerId);
                    try {
                        lockerManager.getLocker(lockerId).storeItem(contents);
                    } catch (NullPointerException e){
                        System.out.println("Locker Not Found");
                    }
                    break;
                case 3:
                    lockerId = promptUserForLockerID();
                    try {
                        lockerManager.getLocker(lockerId).removeItem();
                    } catch (NullPointerException e){
                        System.out.println("Locker Not Found");
                    }
                    break;
                case 4:
                    lockerManager.displayAllLockers();
                    break;
                case 5:
                    //Remove a Locker
                    lockerId = promptUserForLockerID();
                    lockerManager.removeLocker(lockerId);
            }
        }

    }

    static String promptUserForItem(String lockerId) {
        String contents;
        System.out.println("Enter the item you wish to store");
        contents = console.nextLine();
        return contents;
    }

    static String promptUserForLockerID() {
        String lockerId;
        System.out.println("Enter the Locker ID");
        lockerId = console.nextLine();
        return lockerId;
    }

    static int getMenuOptionsFromUser() {
        System.out.println("Select from the following menu options");
        System.out.println("1. Add a Locker");
        System.out.println("2. Store an Item in a Locker");
        System.out.println("3. Retrieve an item from a Locker");
        System.out.println("4. Display all Lockers");
        System.out.println("5. Remove a Locker");
        System.out.println("Any key to exit");
        int userChoice;
        try {
            userChoice = Integer.parseInt(console.nextLine());
            if (userChoice > 5 || userChoice < 1){
                throw new IllegalArgumentException();
            }
            return userChoice;
        }
        catch (NumberFormatException e){
            System.out.println("Input not Recognized. Exiting Program.");
        }
        catch (IllegalArgumentException e){
            System.out.println("Not a valid option Exiting Locker Manager");
        }
        System.exit(0);
        return 0;
    }

}
