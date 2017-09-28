package com.lab.qa.store;

import com.lab.qa.store.Adapters.TimeGenerator;
import com.lab.qa.store.models.Manager;
import com.lab.qa.store.models.Stock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final String DATA_BASE_FILE_NAME = "db.csv";
        final String REPORT_FILE_NAME = "report.txt";
        final int WORK_HOURS_START = 8;
        final int WORK_HOURS_END = 21;
        final int MAX_CUSTOMERS_PER_HOUR = 10;
        final int MAX_ORDERS_PER_CUSTOMER = 10;
        final int DAYS = 30;

        if (DATA_BASE_FILE_NAME.equals(REPORT_FILE_NAME)){
            System.out.println("Wrong file name for database or report file\nPlease try again");
            System.exit(1);
        }

        Manager manager = new Manager(DATA_BASE_FILE_NAME, WORK_HOURS_START, WORK_HOURS_END);
        TimeGenerator timeGenerator = new TimeGenerator();
        Calendar calendar = Calendar.getInstance();
        Random rnd = new Random();
        int id;
        int quantity;

        System.out.format("<<Store is initialized>>\nWe are open from tomorrow for %d days\n\n", DAYS);
        for (int day = 0; day < DAYS; day++){
            calendar.add(Calendar.DATE, 1);
            ArrayList<Calendar> customersPerDay = timeGenerator.generateCustomers(calendar,WORK_HOURS_START, WORK_HOURS_END, MAX_CUSTOMERS_PER_HOUR);

            for (Calendar customerVisitTime : customersPerDay) {
               id = rnd.nextInt(Stock.numOfProducts) + 1;
               quantity = rnd.nextInt((MAX_ORDERS_PER_CUSTOMER < 0 ? -MAX_ORDERS_PER_CUSTOMER : MAX_ORDERS_PER_CUSTOMER) + 1);
               manager.actionSellProduct(id, quantity, customerVisitTime);
            }
            manager.actionEndOfDay();
        }
        manager.saveReport(REPORT_FILE_NAME);
        manager.updateDataBase(DATA_BASE_FILE_NAME);

        System.out.println("<<Store is closed now>>");
    }
}
