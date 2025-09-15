package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class VendingMachineBinTest {

    @Test
    void vendProduct() {
        // Arrange
        VendingMachineBin bin = new VendingMachineBin();
        Product apple = new Product();
        apple.setItemName("Apple");
        apple.setPrice(.25);
        //Act
        bin.loadProduct(apple);
        Product result = bin.vendProduct();
        //Assert
        assertEquals("Apple",result.getItemName());

    }
}