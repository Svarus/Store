package com.lab.qa.store;

import com.lab.qa.store.models.Manager;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.loadProducts();

        System.out.println("End of program");
    }
}
