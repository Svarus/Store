package com.lab.qa.store.Adapters;

import java.util.*;

public class TimeGenerator {
    private ArrayList<Integer> hours = new ArrayList<Integer>();
    private ArrayList<Integer> minutes = new ArrayList<Integer>();
    private ArrayList<String> time = new ArrayList<String>();

    private ArrayList<Calendar> calendars = new ArrayList<Calendar>();

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

    public ArrayList<Calendar> generateVisitors(Calendar calendar, int startHour, int endHour, int maxCustomersCount) {
        fillDigits(startHour, endHour, hours);  //every hour

        Calendar calendarAdded;
        Random rnd = new Random();
        int customersCount;

        calendars.clear();

        for (int i = 0; i < endHour - startHour; i++) {
            customersCount = rnd.nextInt(maxCustomersCount);
            generateDigits(0, 59, customersCount, minutes);
            Collections.sort(minutes);

            for (int j = 0; j < customersCount; j++) {
                calendarAdded = (Calendar) calendar.clone();

                calendarAdded.set(Calendar.HOUR_OF_DAY, hours.get(i) + 0);
                calendarAdded.set(Calendar.MINUTE, minutes.get(j));
                calendars.add(calendarAdded);
            }
        }

        return calendars;
    }
}
