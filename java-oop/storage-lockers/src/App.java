import java.util.ConcurrentModificationException;
import java.util.Scanner;
public class App {
    static Scanner console = new Scanner(System.in);
    public static void main(String[] args) {
        String lockerId = "";
        String contents = "";
        LockerManager lockerManager = new LockerManager();
        while (true) {
            switch (getMenuOptionFromUser()) {
                case 1:
                    lockerId = promptUserForLockerID();
                    lockerManager.addLocker(lockerId);
                    break;
                case 2:
                    lockerId = promptUserForLockerID();
                    try{
                        lockerManager.removeLocker(lockerId);
                    } catch (ConcurrentModificationException e) {
                        System.out.println("Locker Not Found.");
                    }
                    break;
                case 3:
                    lockerId = promptUserForLockerID();
                    contents = promptUserForItem();
                    try {
                        lockerManager.getLocker(lockerId).storeItem(contents);
                    } catch (NullPointerException e) {
                        System.out.println("Locker Not Found.");
                    }
                    break;
                case 4:
                    lockerId = promptUserForLockerID();
                    try {
                        lockerManager.getLocker(lockerId).removeItem();
                    } catch (NullPointerException e) {
                        System.out.println("Locker Not Found.");
                    }
                    break;
                case 5:
                    lockerManager.displayLockers();
                    break;
            }
        }
    }

    static String promptUserForLockerID(){
        String lockerID;
        System.out.println("Enter the locker ID");
        lockerID = console.nextLine();
        return lockerID;
    }
    static String promptUserForItem(){
        String item;
        System.out.println("Enter the lockerID");
        item = console.nextLine();
        return item;
    }
    static  int getMenuOptionFromUser(){
        System.out.println("Hello and welcome! Select from the following menu options");
        System.out.println("1. Add a Locker");
        System.out.println("2. Remove a Locker");
        System.out.println("3. Store an item in Locker");
        System.out.println("4. Retrieve an item from Locker");
        System.out.println("5. Display all Lockers");
        System.out.println("Press Any Key to Exit");
        int userChoice;
        try{
            userChoice = Integer.parseInt(console.nextLine());
            if(userChoice<1 || userChoice> 5 ){
                throw new IllegalArgumentException();
            }
            return userChoice;
        } catch (NumberFormatException e) {
            System.out.println("Thank you for using Locker Manager");
        } catch (IllegalArgumentException e) {
            System.out.println("Thank you for using Locker Manager");
        }
        System.exit(0);
        return 0;
    }
}