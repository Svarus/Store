package com.lab.qa.store.models;

import java.util.HashMap;

public class Stock {
    private HashMap<String, Product> products = new HashMap<>();
    private double income;
    private double outcome;
    private double profit;

    void addProduct(Product product) {
        products.put(product.getName(), product);
    }

    HashMap<String, Product> getAllProducts(){
        return products;
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    public double getIncome() {
        return income;
    }

    public double getOutcome() {
        return outcome;
    }

    public double getProfit() {
        return profit;
    }
}
