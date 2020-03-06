package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Sale {
    static HashMap<Item, Integer> shoppingList = new HashMap();
    double totalPrice;
    double moneyGiven;
    double changeDue;
    static int maxNameSize = 5;// this makes formatting the receipt a little easier.
    static Transaction tx = new Transaction();

    public static void main(String[] args) {

    }

    public void sale() {
        while (true) {
            System.out.println("Please enter a number to make a choice \n" +
                    "1) Make a purchase \n" +
                    "2) Checkout and generate receipt");//user input
            if (Menu.intInput() == 1) {
                System.out.println("Enter the id of the item you want to purchase: ");//adding to "basket"
                int id = Menu.intInput();//takes input of next id
                addToSale(id);//adding new id to sale
            } else {//sale finished creating receipt
                System.out.println("Generating receipt");
                Receipt(shoppingList);//pass shopping list into receipt
                System.out.println("Sale complete");
                break;
            }
        }
    }

    public static void addToSale(int id) {

        Item item = tx.findItem(id);
        if (item == null) {// if item does not exist in db
            System.out.println("id not found");
            return;
        }
        //if item does exist
        if (item.getStock() != 0) {//checks item stock is greater than 0
            if (shoppingList.containsKey(item)) {// if item is already in the hashmap, add one to quantity
                shoppingList.put(item, shoppingList.get(item) + 1);

            } else {//if it is a new item set quantity to one
                shoppingList.put(item, 1);
            }
            tx.updateStock(id, item.getStock() - 1);//update stock in db
        } else {
            System.out.println("This item is out of stock.");
        }
    }

    public void Receipt(HashMap<Item, Integer> shoppingList) {
        System.out.println("Please enter \n 1: to print receipt to command line and save it to a separate file \n " +
                "2: print the receipt to command line and not to a separate file");
        int receiptChoice = Menu.intInput();

        for (Item item : shoppingList.keySet()) { //loop through every item bought
            totalPrice = totalPrice + item.getSell_price();
        }
        System.out.println("Your total is: £" + totalPrice);

        System.out.println("Enter money given: ");
        moneyGiven = Menu.doubleInput();//asks how much money was given

        if(moneyGiven < totalPrice){
            System.out.println("Your price is: £" + totalPrice + " Please give the correct amount: ");
            moneyGiven = Menu.doubleInput();//asks how much money was given
            changeDue = moneyGiven - totalPrice;//calculate change
        } else if(moneyGiven == totalPrice){
            changeDue = moneyGiven - totalPrice;//calculate change
        }else {
            changeDue = moneyGiven - totalPrice;//calculate change
        }

        System.out.println("\n~~~~~~ Thank You for Shopping at Tricky Trinkets ~~~~~~~");// this can be any business name
        System.out.format("+--------------------------------+----------+----------+%n");
        System.out.format("| Item                           | Quantity | Price    |%n");
        System.out.format("+--------------------------------+----------+----------+%n");

        String leftAlignFormat = "| %-30s | %-8s | %-8s | %n";

        for (Item item : shoppingList.keySet()) {
            System.out.format(leftAlignFormat, item.getName(), shoppingList.get(item), item.getSell_price());
        }

        System.out.format("+--------------------------------+----------+----------+%n");
        String totalPriceFormat = "| %-41s | %-8s | %n";
        System.out.format(totalPriceFormat, "Total Price", totalPrice);
        System.out.format(totalPriceFormat, "Money Given", moneyGiven);
        System.out.format(totalPriceFormat, "Change Due", changeDue);
        System.out.format("+--------------------------------+----------+----------+%n");

        if (receiptChoice == 1) {
            try {
                FileWriter fileWrite = new FileWriter("receipt.txt");//opens file
                PrintWriter printLine = new PrintWriter(fileWrite);//write to file
                printLine.println("~~~~~~ Thank You for Shopping at Tricky Trinkets ~~~~~~~");
                printLine.println("+--------------------------------+----------+----------+");
                printLine.println("| Item                           | Quantity | Price    |");
                printLine.println("+--------------------------------+----------+----------+");

                String leftAlignFormat2 = "| %-30s | %-8s | %-8s | %n";

                for (Item item : shoppingList.keySet()) {
                    printLine.printf(leftAlignFormat2, item.getName(), shoppingList.get(item), item.getSell_price());
                }

                printLine.println("+--------------------------------+----------+----------+");
                String totalPriceFormat2 = "| %-41s | %-8s | %n";
                printLine.printf(totalPriceFormat2, "Total Price", totalPrice);
                printLine.printf(totalPriceFormat2, "Money Given", moneyGiven);
                printLine.printf(totalPriceFormat2, "Change Due", changeDue);
                printLine.println("+--------------------------------+----------+----------+");
                printLine.close();
            } catch (IOException e) {
                System.out.println("Sorry, currently external receipts are unavailable due to lack of file access");
            }
        }
    }
}
