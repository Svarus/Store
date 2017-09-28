package com.lab.qa.store.Adapters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

public class TimeGenerator {
    private final int START_MINUTE = 0;
    private final int LAST_MINUTE = 60;

    private ArrayList<Integer> hours = new ArrayList<>();
    private ArrayList<Integer> minutes = new ArrayList<>();
    private ArrayList<Calendar> calendars = new ArrayList<>();

    private void generateDigits(int start, int end, int count, ArrayList<Integer> array) {
        Random rnd = new Random();
        int duration = end - start;
        int hour;

        array.clear();
        for (int i = 0; i < count; i++) {
            hour = start + rnd.nextInt(duration);
            array.add(hour);
        }
    }

    private void fillDigits(int start, int end, ArrayList<Integer> array){
        for (int i = start; i < end; i++){
            array.add(i);
        }
    }

    public ArrayList<Calendar> generateCustomers(Calendar calendar, int startHour, int endHour, int maxCustomersCount) {
        //to prevent wrong input
        if (maxCustomersCount < 1){
            maxCustomersCount = 1;
        }
        //every hour
        fillDigits(startHour, endHour, hours);

        Calendar calendarAdded;
        Random rnd = new Random();
        int customersCount;

        calendars.clear();

        for (int i = 0; i < endHour - startHour; i++) {
            customersCount = rnd.nextInt(maxCustomersCount) + 1;
            generateDigits(START_MINUTE, LAST_MINUTE, customersCount, minutes);
            Collections.sort(minutes);

            for (int j = 0; j < customersCount; j++) {
                calendarAdded = (Calendar) calendar.clone();

                calendarAdded.set(Calendar.HOUR_OF_DAY, hours.get(i));
                calendarAdded.set(Calendar.MINUTE, minutes.get(j));
                calendars.add(calendarAdded);
            }
        }

        return calendars;
    }
}
