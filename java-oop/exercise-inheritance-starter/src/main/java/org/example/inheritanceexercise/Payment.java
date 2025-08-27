package org.example.inheritanceexercise;

import java.util.Objects;

public abstract class Payment {
    private int id;
    private double amount;

    public Payment(int id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract boolean processPayment();

    @Override
    public String toString() {
        return "Payment:  " + id + "  Amount:  $" + String.format("%.2f", amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof org.example.inheritanceexercise.Payment payment)) return false;
        return getId() == payment.getId() && Double.compare(payment.getAmount(), getAmount()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}