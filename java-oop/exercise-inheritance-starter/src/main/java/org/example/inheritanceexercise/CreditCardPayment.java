package org.example.inheritanceexercise;

public class CreditCardPayment extends Payment {
    private long accountNumber;
    private String vendor;
    boolean accepted= true;

    public CreditCardPayment(int id, double amount, long accountNumber,String vendor) {
        super(id, amount);
        this.accountNumber = accountNumber;
        this.vendor = vendor;
    }


    public boolean processPayment() {
        accepted = true;
        return true;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        if(accepted) {
            return "\nid: " + getId() +
                    "\nAmount: " + getAmount() +
                    "\nvendor: " + getVendor() +
                    "\nPayment Accepted";
        }
        return "\nid: " + getId() +
                "\nAmount: " + getAmount() +
                "\nvendor: " + getVendor() +
                "\nPayment Declined";
    }
}
