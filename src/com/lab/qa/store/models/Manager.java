package com.lab.qa.store.models;

import com.lab.qa.store.Adapters.FileAdapter;

import java.util.HashMap;

public class Manager {
    private Stock stock;

    public Manager(String dataBaseFileName){
        this.createStock();
        this.loadProducts(dataBaseFileName);
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

    private void loadProducts(String fileName) {
        if (!FileAdapter.loadProducts(stock, fileName)) {
            System.out.println("Can't open database " + fileName);
        }
    }
}
