package org.example.service;

import org.example.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineImplTest {
    VendingMachine vm;
    static Product apple;
    static Product candyBar;
    static Product soda;

    @BeforeAll
    static void buildProducts() {
        apple = new Product("Apple", .50);
        candyBar = new Product("Candy Bar", 1.00);
        soda = new Product("Soda", 2.50);
    }


    @BeforeEach
    void setUp() {
        vm = new VendingMachineImpl();
        vm.loadProduct("1", apple, 3);
        vm.loadProduct("2", candyBar, 3);
        vm.loadProduct("3", soda, 3);

        vm.addMoney(100);
    }

    @Test
    void vendLastItem() {
        //Arrange
        //Vending Machine is ready
        //Act
        vm.vend("1");
        //Assert
        assertEquals(2, vm.getBinQuantity("1"));
        //Act
        vm.vend("1");
        //Assert
        assertEquals(1, vm.getBinQuantity("1"));
        //Act
        vm.vend("1");
        //Assert
        assertEquals(0, vm.getBinQuantity("1"));

    }

    @Test
    void vendProductMoneyBinCorrect() {
        //Arrange
        //Vending Machine is Ready
        //Act
        vm.vend("2");
        //Assert
        assertEquals(1.00, vm.getMoneyBinContents());
        //Act
        vm.vend("2");
        //Assert
        assertEquals(2.00, vm.getMoneyBinContents());
        //Act
        vm.vend("2");
        //Assert
        assertEquals(3.00, vm.getMoneyBinContents());
        //Assert

    }

    @Test
    void loadProductCorrectQuantity() {
        //Arrange
        //Nothing to Arrange
        //Act
        //Nothing to Act on
        //Assert
        assertEquals(3, vm.getBinQuantity("1"));
        assertEquals(3, vm.getBinQuantity("2"));
        assertEquals(3, vm.getBinQuantity("3"));


    }

}