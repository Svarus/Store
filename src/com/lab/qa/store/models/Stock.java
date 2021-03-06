package com.lab.qa.store.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Stock {
    public static int numOfProducts;

    private final int MIN_REQUIRED = 10;
    private final int ITEMS_NEEDED = 150;

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

    Product getProduct(Integer id) {
        return products.get(id);
    }

    void sellProduct(int id, int quantity, double price) {
        Product product = products.get(id);
        product.changeQuantity(quantity);
        product.addIncome(price);
        income += price;
        profit += (price - product.getPrice()) * quantity;
    }

    //debug purpose only
    void showProducts() {
        for (Product product : products.values()) {
            System.out.format("Product %s\n", product.getFullName());
            System.out.format("\t\tsold: %d\n", product.getSoldQuantity());
            System.out.format("\t\tremains: %d\n", product.getQuantity());
            System.out.format("\t\tordered: %d\n", product.getRebuyQuantity());
        }
    }

    ArrayList<String> getReport(){
        ArrayList<String> report = new ArrayList<>();

        for (Product product : products.values()){
            report.add(String.format("%s", product.getFullName()));
            report.add(String.format("\tsold: %d", product.getSoldQuantity()));
            report.add(String.format("\tordered: %d", product.getRebuyQuantity()));
        }

        return report;
    }

    void fillProducts(Manager manager) {
        for (Product product : products.values()) {
            if (product.getQuantity() < MIN_REQUIRED) {
                manager.makeOrder(product, ITEMS_NEEDED - product.getQuantity());
            }
        }
    }

    void addOutcome(double value) {
        outcome += value;
    }

    double getIncome() {
        return income;
    }

    double getOutcome() {
        return outcome;
    }

    double getProfit() {
        return profit;
    }
}
