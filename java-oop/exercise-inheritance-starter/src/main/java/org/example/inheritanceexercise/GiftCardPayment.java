package org.example.inheritanceexercise;

public class GiftCardPayment extends Payment{
    private long accountNumber;
    private double balance, loyaltyPoints;


    public GiftCardPayment(int id, double amount, long accountNumber, double balance, double loyaltyPoints) {
        super(id, amount);
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean processPayment() {
        if(getAmount()> getBalance()){

            return false;
        }else {

            balance -= getAmount();
            loyaltyPoints += 100 * getAmount();
            return true;
        }
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
    @Override
    public String toString() {
        return "\nid: " + getId() +
                "\nAmount: " + getAmount() +
                "\nloyalty points: " + getLoyaltyPoints() +
                "\nbalance: " + getBalance();
    }
}
