package com.lab.qa.store.models;

public interface Product {
    void changeQuantity(int value);
    void addIncome(double value);
    void reBuy(int value);

    double getPrice();
    double getVolume();

    int getQuantity();
    int getSoldQuantity();
    int getRebuyQuantity();
    int getId();

    String getName();
    String getFullName();
    String getCsvString();
    String getCategory();
    String getAdditionalDescription();
}
