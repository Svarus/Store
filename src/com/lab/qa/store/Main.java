package com.lab.qa.store;

import com.lab.qa.store.models.Manager;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        String dataBaseFileName = "db.csv";
        Manager manager = new Manager(dataBaseFileName, 8, 21);

        Calendar calendar = new GregorianCalendar(2013,10,28, 9, 12);

        manager.actionSellProduct(1, 2, calendar);
        manager.actionSellProduct(2, 2, calendar);
        manager.actionEndOfDay();

        System.out.println("End of program");
    }
}
