package com.lab.qa.store.Adapters;

import com.lab.qa.store.models.Product;
import com.lab.qa.store.models.Stock;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
                //System.out.println(line); //debug purpose
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

    public static void stringWriter(ArrayList<String> data, String fileName) {
        try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);
            //Writer fileWriter = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String str : data) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.format("Error writing to file %s", fileName);
            ex.printStackTrace();
        }
    }
}
