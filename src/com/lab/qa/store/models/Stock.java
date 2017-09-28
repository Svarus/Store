package com.lab.qa.store.models;

import java.util.HashMap;

public class Stock {
    static int numOfProducts;
    private HashMap<Integer, Product> products = new HashMap<>();
    private double income;
    private double outcome;
    private double profit;

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    HashMap<Integer, Product> getAllProducts(){
        return products;
    }

    public Product getProduct(Integer id) {
        return products.get(id);
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
