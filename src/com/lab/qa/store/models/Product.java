package com.lab.qa.store.models;

public interface Product {
    String getName();
    double getPrice();
    void changeQuantity(int value);
    int getQuantity();
    int getSoldQuantity();
    int getRebuyQuantity();
    int getId();
    void addIncome(double value);
    double getVolume();
    void reBuy(int value);
    String getFullName();
}
