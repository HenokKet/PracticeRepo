package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineBin {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void loadProduct(Product product) {
        products.add(product);
    }

    public Product vendProduct() {
        Product result = products.get(1);
        products.remove(1);

        return result;
    }
}
