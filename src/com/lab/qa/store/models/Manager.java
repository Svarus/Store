package com.lab.qa.store.models;

import com.lab.qa.store.Adapters.FileAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Manager {
    private Stock stock;
    private int workHoursStart;
    private int workHoursEnd;

    public Manager(String dataBaseFileName, int workHoursStart, int workHoursEnd){
        this.workHoursStart = workHoursStart;
        this.workHoursEnd = workHoursEnd;
        this.createStock();
        this.loadProducts(dataBaseFileName);
    }

    public void setDataBase (Stock stock) {
        this.stock = stock;
    }

    public void addProduct(Product product) {
        stock.addProduct(product);
    }

    public HashMap<Integer, Product> getProducts(){
        return stock.getAllProducts();
    }

    private void createStock() {
        this.stock = new Stock();
    }

    private void loadProducts(String fileName) {
        if (!FileAdapter.loadProducts(stock, fileName)) {
            System.out.println("Can't open database " + fileName);
        }
    }

    public void actionSellProduct(int id, int quantity, Calendar date) {
        Product product = stock.getProduct(id);
        String name = product.getFullName();

        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy 'at' HH:mm");
        System.out.format("%s | Customer want to buy %d of %s. Processing order...\n", ft.format(date.getTime()), quantity, name);

        int itemsAvailable = product.getQuantity();
        if(itemsAvailable < quantity) {
            System.out.format("\tCan't sell %d of %s product. There are only %d items available for now\n", quantity, name, itemsAvailable);
        } else {
            int hour = date.get(Calendar.HOUR_OF_DAY);
            if (hour < workHoursStart || hour > workHoursEnd) {
                System.out.format("\tSorry, stock is closed now. Working hours are %d to %d\n", workHoursStart, workHoursEnd);
            } else {
                double price = product.getPrice();

                double percent = 0.1;	//standard mark-up 10%
                boolean discount = false;
                int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
                    percent = 0.15;	//weekend mark-up 15%
                }
                //int hour = date.get(Calendar.HOUR_OF_DAY);
                if ( hour >= 18 && hour < 20) {
                    percent = 0.08;	//18:00-20:00 mark-up 8%
                }
                price+= price * percent;

                if (quantity > 2) {
                    discount = true;
                }
                for (int i = 0; i < quantity; i++) {

                    if (discount && i >= 2) {
                        percent = 0.07;	//every 2+ item mark-up 7%
                        price = product.getPrice();
                        price += price * percent;
                        discount = false;
                    }

                    System.out.format("\t%s sold for %.2f (%.0f%% mark-up)\n", name, price, percent * 100);
                    stock.sellProduct(id, 1, price);
                }
            }
        }
    }

    public void actionEndOfDay() {
        //System.out.println("\nEnd of Day Report");

        //stock.showProducts();
        stock.fillProducts(this);
        //stock.showProducts();

        /*System.out.format("\nProfit: %.2f\n", stock.getProfit());
        System.out.format("Total outcome: %.2f\n", stock.getOutcome());
        System.out.format("Total income: %.2f\n\n", stock.getIncome());*/
    }

    public void saveReport(String fileName) {
        ArrayList<String> messages;

        messages = stock.getReport();

        messages.add(String.format("\nProfit: %.2f", stock.getProfit()));
        messages.add(String.format("Total outcome: %.2f", stock.getOutcome()));
        //messages.add(String.format("Total income: %.2f", stock.getIncome()));
        FileAdapter.stringWriter(messages, fileName);
    }

    void makeOrder(Product product, int i) {
        //System.out.format("Product %s left: %d. Making order for additional %d items\n", product.getName(), product.getQuantity(), i);
        stock.addOutcome(product.getPrice() * i);
        product.reBuy(i);
    }

    public void updateDataBase(String fileName) {
        ArrayList<String> messages = new ArrayList<>();

        HashMap<Integer, Product> products = stock.getAllProducts();
        for (Product product : products.values()){
            String str = product.getCsvString();
            messages.add(str);
        }
        FileAdapter.stringWriter(messages, fileName);
    }
}
