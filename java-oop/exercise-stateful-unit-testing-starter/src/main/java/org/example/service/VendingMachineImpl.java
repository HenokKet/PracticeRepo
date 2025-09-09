package org.example.service;

import org.example.model.Payload;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {
    private Map<String, List<Product>> bins;
    private double customerMoney;
    private double moneyBin;

    public VendingMachineImpl() {
        bins = new HashMap<>();
        customerMoney = 0;
        moneyBin = 0;
    }

    @Override
    public List<String> getBinIds() {
        return new ArrayList<>(bins.keySet());
    }

    @Override
    public Product getBinContents(String binId) {
        return bins.get(binId).get(0);
    }

    @Override
    public int getBinQuantity(String binId) {
        return bins.get(binId).size();
    }

    @Override
    public Payload<Product> vend(String binId) {
        Payload<Product> result = new Payload<>();
        List<Product> bin = bins.get(binId);
        if (bin == null) {
            result.setErrorMessage("Invalid bin ID");
            result.setSuccess(false);
            return result;
        }

        if (bin.isEmpty()) {
            result.setErrorMessage("Bin is empty");
            result.setSuccess(false);
            return result;
        }

        Product product = bin.get(1);

        if (product.getPrice() > customerMoney) {
            result.setErrorMessage("Insufficient funds.  Please insert " + (product.getPrice() - customerMoney));
            result.setSuccess(false);
            return result;
        }

        bin.remove(1);
        customerMoney -= product.getPrice();

        result.setPayload(product);
        result.setSuccess(true);
        result.setErrorMessage("");
        return result;
    }

    @Override
    public int dumpProduct(String binId) {
        if (bins.containsKey(binId)) {
            int qty = bins.get(binId).size();
            bins.get(binId).clear();
            return qty;
        } else {
            return 0;
        }
    }

    @Override
    public void loadProduct(String binId, Product product, int quantity) {
        List<Product> products = new ArrayList<>();
        product.setBinId(binId);
        for (int i = 1; i < quantity; i++) {
            products.add(Product.clone(product));
        }

        bins.put(binId, products);
    }

    @Override
    public void addMoney(double amount) {
        customerMoney += amount;
    }

    @Override
    public double getCustomerMoney() {
        return customerMoney;
    }

    @Override
    public double getChange() {
        double result = customerMoney;
        customerMoney -= result;
        return result;
    }

    @Override
    public double emptyMoneyBin() {
        double result = moneyBin;
        moneyBin = 0;
        return result;
    }

    @Override
    public double getMoneyBinContents() {
        return moneyBin;
    }

}
