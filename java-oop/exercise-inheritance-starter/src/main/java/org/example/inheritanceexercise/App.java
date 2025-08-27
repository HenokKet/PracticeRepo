package org.example.inheritanceexercise;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Payment> payments = new ArrayList<>();

        //Create your sample payments here

        //Payments Report
        for (Payment p : payments) {
            System.out.println(p.toString());
        }

        //Payment Processing Report
        for (Payment p : payments) {
            p.processPayment();
        }

        //Uncomment this section after implementing GiftCardPayment to verify that balances are correct after processing
//        //Post processing gift card balance check
//        for (Payment p : payments) {
//            if (p instanceof GiftCardPayment) {
//                System.out.println(p.toString());
//            }
//        }
    }
}
