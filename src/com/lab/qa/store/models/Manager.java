package com.lab.qa.store.models;

import com.lab.qa.store.Adapters.FileAdapter;

import java.util.HashMap;

public class Manager {
    private Stock stock;

    public Manager(){
        this.createStock();
        this.loadProducts();
    }

    public void setDataBase (Stock stock) {
        this.stock = stock;
    }

    public void addProduct(Product product) {
        stock.addProduct(product);
    }

    public HashMap<Integer, Product> getProducts(){
        return stock.getAllProducts();
    }

    private void createStock() {
        this.stock = new Stock();
    }

    private void loadProducts() {
        /*Product product1 = new AlcoholDrink("White", 10, "red wines", 0.95, "5%", 5);
        Product product2 = new SoftDrink("Mineral water Good", 1, "mineral waters", 0.3, "water, minerals", 14);
        stock.addProduct(product1);
        stock.addProduct(product2);*/
        String fileName = "db.csv";
        if (!FileAdapter.loadProducts(stock, fileName)) {
            System.out.println("Can't open database " + fileName);
        }
    }
}
