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

    void showProducts() {
        for (Product product : products.values()) {
            System.out.format("Product %s sold: %d\n", product.getFullName(), product.getSoldQuantity());
            System.out.format("Product %s left: %d\n", product.getFullName(), product.getQuantity());
            System.out.format("Product %s rebuyed: %d\n", product.getFullName(), product.getRebuyQuantity());
        }
    }

    void fillProducts(Manager manager) {
        for (Product product : products.values()) {
            if (product.getQuantity() < 10) {
                manager.makeOrder(product, 150 - product.getQuantity());
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
