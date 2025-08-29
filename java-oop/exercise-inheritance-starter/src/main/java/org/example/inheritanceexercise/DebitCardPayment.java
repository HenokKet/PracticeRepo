package org.example.inheritanceexercise;

public class DebitCardPayment extends Payment{
    private long routingNumber;
    private long accountNumber;
    private String bankName;
    private double processingFee;
    boolean accepted = true;

    public DebitCardPayment(int id, double amount, long routingNumber, long accountNumber,String bankName,double processingFee  ) {
        super(id, amount);
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.bankName= bankName;
        this.processingFee = processingFee;
    }

    public boolean processPayment() {
        accepted =true;
        return true;
    }


    public long getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(long routingNumber) {
        this.routingNumber = routingNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    @Override
    public String toString() {
        if(accepted) {
            return "\nid: " + getId() +
                    "\nAmount: " + getAmount() +
                    "\nBank Name: " + getBankName()+
                    "\nPayment Accepted";
        }
        return "\nid: " + getId() +
                "\nAmount: " + getAmount() +
                "\nBank Name: " + getBankName()+
                "\nPayment Declined";
    }
}
