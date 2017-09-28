package com.lab.qa.store.models;

public interface Product {
    String getName();
    double getPrice();
    void changeQuantity(int value);
    int getQuantity();
    int getSoldQuantity();
    int getRebuyQuantity();
}
