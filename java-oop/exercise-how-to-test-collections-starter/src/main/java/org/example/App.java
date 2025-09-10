package org.example;

import org.example.model.Product;
import org.example.model.VendingMachineBin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        VendingMachineBin bin = new VendingMachineBin();

        Product apple = new Product();
        apple.setItemName("Apple");
        apple.setPrice(.25);

        //Load 5 apples
        bin.loadProduct(apple);
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));
        bin.loadProduct(Product.clone(apple));

        //Vend 5 apples
        bin.vendProduct();
        bin.vendProduct();
        bin.vendProduct();
        bin.vendProduct();

        // ERROR
        bin.vendProduct();
    }
}
