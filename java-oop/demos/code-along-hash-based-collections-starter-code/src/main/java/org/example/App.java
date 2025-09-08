package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        BirthdayManager myManager = new BirthdayManager();

        System.out.println("Adding a new friend Bob");
        Friend bob = new Friend("Bob", "08/01/2002");
        myManager.addFriend(bob);

        System.out.println("Adding a new friend Jane");
        Friend jane = new Friend("Jane", "04/01/2001");
        myManager.addFriend(jane);

        System.out.println(myManager.getFriends());

        System.out.println("Give Jane a gift of a pony");
        myManager.addGift(jane, new Gift("Pony", "Small"));

        System.out.println("Give Bob a gift of flowers");
        myManager.addGift(bob, new Gift("Flowers", "Extra Large"));

        System.out.println("Give Bob a gift of candy");
        myManager.addGift(bob,new Gift("Candy", "Small"));

        System.out.println("Remove Bob from the BirthdayManager");
        myManager.removeFriend(bob);

        System.out.println(myManager.getFriends());


    }
}
