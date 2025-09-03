package org.example;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderManagerTest {

    List<Item> items;
    OrderManager mgr;

    @BeforeEach
    public void setup() {
        mgr = new OrderManager();
        items = new ArrayList<Item>();
        items.add(new Item());
        items.add(new Item());
        items.get(0).setName("Playing Cards");
        items.get(0).setPrice(5.89);
        items.get(1).setName("Loaded Dice");
        items.get(1).setPrice(6.46);
    }

    @Test
    public void testOrderTotal() {
        double expected = 9.82; //((5.89 + 6.46) * 0.75) * 1.06
        double result = mgr.orderTotal(items, 0.06, 0.25);
        assertEquals(expected, result, 0.01);
    }



}