package org.example;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Step 1: Define the Product Class
// - Represents a product inside the vending machine.
class Product {
    private String itemName;
    private double price;

    // Constructor
    public Product() {}

    public Product(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }

    // Getters and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Clone method to create a copy of a product
    public static Product clone(Product product) {
        return new Product(product.getItemName(), product.getPrice());
    }
}

// Step 2: Define the VendingMachineBin Class (With a Bug!)
class VendingMachineBin {
    private List<Product> products;

    // Constructor to initialize an empty list
    public VendingMachineBin() {
        this.products = new ArrayList<>();
    }

    // Getter method to retrieve the list of products
    public List<Product> getProducts() {
        return products;
    }

    // Method to load a product into the bin
    public void loadProduct(Product product) {
        products.add(product);
    }

    // Method to vend (dispense) a product from the bin (Contains Bug)
    public Product vendProduct() {
        Product result = products.get(1); // ERROR: This fails when only one item is left
        products.remove(1); // ERROR: Trying to remove an item that may not exist
        return result;
    }
}

// Step 3: Define the Main Class (App) - This Will Trigger the Bug!
public class Main {
    public static void main(String[] args) {
        // Create a VendingMachineBin instance
        VendingMachineBin bin = new VendingMachineBin();

        // Create a Product instance for an Apple
        Product apple = new Product();
        apple.setItemName("Apple");
        apple.setPrice(0.25);

        // Load 5 apples into the vending machine bin
        bin.loadProduct(apple);
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));

        // Vend 5 apples (Bug appears on the last vend)
        bin.vendProduct();
        bin.vendProduct();
        bin.vendProduct();
        bin.vendProduct();

        bin.vendProduct();

        System.out.println("All products vended successfully! (This message will NOT be reached)");
    }
}

// Step 4: Create Unit Tests in VendingMachineBinTest (Will Fail)
class VendingMachineBinTest {
    @Test
    void vendProduct() {
        // Create a new VendingMachineBin instance
        VendingMachineBin bin = new VendingMachineBin();

        // Create a Product instance
        Product apple = new Product();
        apple.setItemName("Apple");
        apple.setPrice(0.25);

        // Load multiple products into the vending machine
        bin.loadProduct(apple);
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));

        // Use assertDoesNotThrow() to confirm that vending products does not throw an error.
        assertDoesNotThrow(() -> bin.vendProduct());
        assertDoesNotThrow(() -> bin.vendProduct());
        assertDoesNotThrow(() -> bin.vendProduct());
        assertDoesNotThrow(() -> bin.vendProduct());
        assertDoesNotThrow(() -> bin.vendProduct());
    }
}
