package com.lab.qa.store.models;

public class AlcoholDrink implements Product {
    private int id;
    private String name;
    private double price;
    private String category;
    private double volume;
    private String strength;
    private int quantity;

    //for Reports
    private int soldQuantity;
    private int rebuyQuantity;

    public AlcoholDrink(String name, double price, String category,
                   double volume, String strength, int quantity) {
        Stock.numOfProducts++;
        this.id = Stock.numOfProducts;

        this.name = name;
        this.price = price;
        this.category = category;
        this.volume = volume;
        this.strength = strength;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getSoldQuantity() {
        return soldQuantity;
    }

    @Override
    public int getRebuyQuantity() {
        return rebuyQuantity;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void changeQuantity(int value) {

    }
}
