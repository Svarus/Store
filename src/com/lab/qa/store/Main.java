package com.lab.qa.store;

import com.lab.qa.store.models.Manager;

public class Main {

    public static void main(String[] args) {
        String dataBaseFileName = "db.csv";
        Manager manager = new Manager(dataBaseFileName);

        System.out.println("End of program");
    }
}
