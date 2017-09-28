package com.lab.qa.store.Adapters;

import com.lab.qa.store.models.Product;
import com.lab.qa.store.models.Stock;

import java.io.*;
import java.util.ArrayList;

public class FileAdapter {
    public static void loadProducts(Stock stock, String fileName) {

        File file = new File(fileName);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file " + fileName + ". Please check value for final String DATA_BASE_FILE_NAME if it's possible");
            System.out.println("Program is stopped for now.");
            System.exit(1);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line;
        try {
            while ((line = br.readLine()) != null) {
                //skip empty lines
                if (line.length() > 1) {
                    Product product = CSVHandler.convertToProduct(line);
                    stock.addProduct(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stringWriter(ArrayList<String> data, String fileName) {
        try {
            // Assume default encoding
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
