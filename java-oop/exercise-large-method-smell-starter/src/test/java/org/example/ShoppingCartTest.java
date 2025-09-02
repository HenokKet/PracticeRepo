package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    ShoppingCart testCart;
    Item[] testItems;
    Item testItem1;
    Item testItem2;
    Item testItem3;

    @BeforeEach
    public void setup() {
        testItem1 = new Item();
        testItem1.setName("Spinny Top");
        testItem1.setPrice(2.00);

        testItem2 = new Item();
        testItem2.setName("Protective Eyewear");
        testItem2.setPrice(10.00);

        testItem3 = new Item();
        testItem3.setName("Red Ryder BB Gun");
        testItem3.setPrice(50.00);

        testItems = new Item[3];
        testItems[0] = testItem1;
        testItems[1] = testItem2;
        testItems[2] = testItem3;

        testCart = new ShoppingCart();
    }

    @Test
    void checkoutShoppingCart() {
        double taxRate = .10;
        double discount = .30;
        double expected = 47.74;
        double precision = .001;
        // Because binary math and decimal math don't always work out precisely the same
        // assertEquals for floating point numbers allows a third parameter for specifying
        // "close enough".  In this case, .001 means we will be accurate to a tenth of a cent.
        assertEquals(expected, testCart.checkoutShoppingCart(testItems, taxRate, discount), precision);
    }
}