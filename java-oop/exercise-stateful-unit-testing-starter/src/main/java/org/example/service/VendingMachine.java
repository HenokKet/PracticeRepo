package org.example.service;

import org.example.model.Payload;
import org.example.model.Product;

import java.util.List;

public interface VendingMachine {
    public List<String> getBinIds();
    Product getBinContents(String binId);
    int getBinQuantity(String binId);
    public Payload<Product> vend(String binId);
    public int dumpProduct(String binId);
    public void loadProduct(String binId, Product product, int quantity);
    public void addMoney(double amount);
    public double getCustomerMoney();
    public double getChange();
    public double emptyMoneyBin();
    public double getMoneyBinContents();
}
