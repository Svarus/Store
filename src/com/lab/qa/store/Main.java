package com.lab.qa.store;

import com.lab.qa.store.Adapters.TimeGenerator;
import com.lab.qa.store.models.Manager;
import com.lab.qa.store.models.Stock;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String dataBaseFileName = "db.csv";
        Manager manager = new Manager(dataBaseFileName, 8, 21);
        TimeGenerator timeGenerator = new TimeGenerator();

        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm");

        Calendar calendar = Calendar.getInstance();

        Random rnd = new Random();
        int id;
        int quantity;

        for (int day = 0; day < 2; day++){
            calendar.add(Calendar.DATE, day);
            ArrayList<Calendar> visitorsPerDay = timeGenerator.generateVisitors(calendar,8, 21, 10);

            for (Calendar vis : visitorsPerDay) {
                //System.out.println(sdf.format(vis.getTime()));
               id = rnd.nextInt(Stock.numOfProducts) + 1;
               quantity = rnd.nextInt(10);
               manager.actionSellProduct(id, quantity, vis);
            }
            manager.actionEndOfDay();
        }

        String reportFileName = "report.txt";
        manager.saveReport(reportFileName);
/*
        manager.actionSellProduct(1, 2, visitorsPerDay.get(0));
        manager.actionSellProduct(2, 2, visitorsPerDay.get(9));

        manager.actionEndOfDay();*/

        System.out.println("End of program");
    }
}
