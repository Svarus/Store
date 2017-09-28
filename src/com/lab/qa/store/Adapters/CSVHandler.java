package com.lab.qa.store.Adapters;

import com.lab.qa.store.models.AlcoholDrink;
import com.lab.qa.store.models.Product;
import com.lab.qa.store.models.SoftDrink;

import java.util.ArrayList;
import java.util.List;

class CSVHandler {
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    static Product convertToProduct(String line){
        Product product;

        List<String> data = parseLine(line, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
        String name = data.get(0);
        double price = Double.parseDouble(data.get(1));
        String category = data.get(2);
        double volume = Double.parseDouble(data.get(3));
        String strength = data.get(4);
        int quantity = Integer.parseInt(data.get(5).replaceAll("\\s+",""));

        if (strength.contains("%")) {
            product = new AlcoholDrink(name, price, category, volume, strength, quantity);
        } else {
            product = new SoftDrink(name, price, category, volume, strength, quantity);
        }

        return product;
    }

    private static List<String> parseLine(String line, char separators, char customQuote) {
        ArrayList<String> result = new ArrayList<>();

        if (line == null && line.isEmpty()) {
            return result;
        }

        StringBuffer currentValue = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = line.toCharArray();

        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            currentValue.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        currentValue.append(ch);
                    }
                }
            } else {
                if (ch == customQuote) {
                    inQuotes = true;

                    if (chars[0] != '"' && customQuote == '\"') {
                        currentValue.append('"');
                    }

                    if (startCollectChar) {
                        currentValue.append('"');
                    }
                } else if (ch == separators) {
                    result.add(currentValue.toString());

                    currentValue = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\n') {
                    break;
                } else {
                    currentValue.append(ch);
                }
            }
        }
        result.add(currentValue.toString());
        return result;
    }
}