package org.example.model;

public class Product {
    private String itemName;
    private double price;

    public static Product clone(Product p) {
        Product result = new Product();
        result.setPrice(p.price);
        result.setItemName(p.itemName);
        return result;
    }

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
}
