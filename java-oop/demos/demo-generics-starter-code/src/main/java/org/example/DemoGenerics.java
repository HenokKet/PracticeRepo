package org.example;

// Step 1: Define a Generic Container Class
// Create a class that can store and retrieve any type of object using generics.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Container<T> {
    // Declare a private variable to hold the generic type value.
    private T value;
    // Define a method to set the value of the container.
    public void set(T value){
        this.value = value;
    }
    // Define a method to get and return the value stored in the container.
    public T get(){
        return value;
    }
}

public class DemoGenerics {
    public static void main(String[] args) {
        // Step 2: Use the Generic Container
        // Create a Container object that holds Integers.
        Container<Integer> wholeNumberContainer = new Container<>();

        // Store a number inside the container.
        wholeNumberContainer.set(42);
        // Retrieve and print the stored number.
        System.out.println("Number Stored in Container: " + wholeNumberContainer.get());
        // Step 3: Use an ArrayList to Store Orders
        // Declare an ArrayList of Strings to store order IDs.
        ArrayList<String> orders = new ArrayList<>();

        // Add several order IDs to the list.
        orders.add("Order # 1001");
        orders.add("Order # 1002");
        orders.add("Order # 1003");

        // Print out all the orders using a loop.
        for (String order: orders) {
            System.out.println(order);
        }
        // Step 4: Use a Queue to Process Orders
        // Declare a Queue of Strings to store customer orders.
        Queue<String> orderQueue = new LinkedList<>();
        // Add customer orders to the queue.
        orderQueue.add("Customer 1 - Velvet Elvis Painting");
        orderQueue.add("Customer 2 - Iggy Pop Poster");
        orderQueue.add("Customer 3 - Metallica Poster");

        // Process orders one by one using a loop and print each processed order.
        System.out.println("\n Processing Orders: ");
        System.out.println(orderQueue.peek());
        while (!orderQueue.isEmpty()){
            System.out.println("Processing" + orderQueue.poll());
        }

    }
}
