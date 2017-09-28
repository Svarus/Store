package com.lab.qa.store.Adapters;

import com.lab.qa.store.models.Product;
import com.lab.qa.store.models.Stock;

import java.io.*;

public class FileAdapter {
    public static boolean loadProducts(Stock stock, String fileName) {
        boolean result;

        File fin = new File(fileName);
        FileInputStream fis;
        try {
            fis = new FileInputStream(fin);
        } catch (FileNotFoundException e) {
            result = false;
            e.printStackTrace();
            return result;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                Product product = CSVHandler.convertToProduct(line);
                stock.addProduct(product);
            }
            result = true;
        } catch (IOException e) {
            result = false;
            e.printStackTrace();
            return result;
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
