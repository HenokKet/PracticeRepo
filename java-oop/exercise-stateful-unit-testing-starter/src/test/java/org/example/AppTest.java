package org.example;

import org.example.model.Payload;
import org.example.model.Product;
import org.example.service.VendingMachine;
import org.example.service.VendingMachineImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private static Product apple;
    private static Product candyBar;
    private static Product soda;
    VendingMachine vm;

    @BeforeAll
    static void buildProducts(){
       apple = new Product("Apple", .50);
       candyBar = new Product("Candy Bar", 1.00);
       soda = new Product("Soda", 2.00);
    }
    @BeforeEach
    void setUp() {
        vm = new VendingMachineImpl();
        vm.loadProduct("1",apple,3);
        vm.loadProduct("2",candyBar,3);
        vm.loadProduct("3", soda, 3);
        vm.addMoney(50);
    }

    @Test
    void vend(){
        // Arrange
        // Act
        vm.vend("1");
        // Assert
        assertEquals(2,vm.getBinQuantity("1"));
        // Act
        vm.vend("1");
        // Assert
        assertEquals(1,vm.getBinQuantity("1"));
        // Act
        vm.vend("1");
        // Assert
        assertEquals(0,vm.getBinQuantity("1"));
    }
    @Test
    void vendMoneyBin(){
        // Act
        vm.vend("2");
        // Assert
        assertEquals(1.00,vm.getMoneyBinContents());
    }
    @Test
    void correctCount(){
        // Assert
        assertEquals(3,vm.getBinQuantity("1"));
    }
}