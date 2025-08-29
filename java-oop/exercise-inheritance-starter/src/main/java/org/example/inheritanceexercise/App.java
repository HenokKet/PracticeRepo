package org.example.inheritanceexercise;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Payment> payments = new ArrayList<>();

        //Create your sample payments here
        CreditCardPayment ccPay = new CreditCardPayment(1,500,2333,"Visa");
        payments.add(ccPay);
        DebitCardPayment dcPay = new DebitCardPayment(2,600.75,123456,654321,"Chase",0.00);
        payments.add(dcPay);
        GiftCardPayment gcPay = new GiftCardPayment(3,700.00,5677,500.00,2000);
        payments.add(gcPay);
        //Payments Report
        for (Payment p : payments) {
            System.out.println(p.toString());
        }

        //Payment Processing Report
        for (Payment p : payments) {
            p.processPayment();
        }

        //Uncomment this section after implementing GiftCardPayment to verify that balances are correct after processing
        //Post processing gift card balance check
        for (Payment p : payments) {
            if (p instanceof GiftCardPayment) {
                System.out.println(p.toString());
            }
        }
    }
}
