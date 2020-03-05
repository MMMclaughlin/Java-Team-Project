package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Sale {
    static HashMap<Item, Integer> shoppingList = new HashMap();
    double totalPrice;
    double moneyGiven;
    double changeDue;
    static int maxNameSize = 5;// this makes formatting the receipt a little easier.
    static Transaction tx = new Transaction();
    public static void main(String[] args){

    }
    public void sale(){
        while(true){
            System.out.println("Please enter a number to make a choice \n" +
                    "1) Make a purchase \n" +
                    "2) Checkout and generate receipt");//user input
            if (Menu.intInput() ==1){
                System.out.println("Enter the id of the item you want to purchase: ");//adding to "basket"
                int id = Menu.intInput();//takes input of next id
                addToSale(id);//adding new id to sale
            }
            else {//sale finished creating receipt
                System.out.println("Generating receipt");
                Receipt(shoppingList);//pass shopping list into receipt
                System.out.println("Sale complete");
                break;
            }
        }
    }
    public static void addToSale(int id){

        Item item = tx.findItem(id);
        if (item == null){// if item does not exist in db
            System.out.println("id not found");
            return;
        }
        //if item does exist
        if (item.stock!=0) {//checks item stock is greater than 0
            if (shoppingList.containsKey(item)) {// if item is already in the hashmap, add one to quantity
                shoppingList.put(item,shoppingList.get(item)+1);

            }
            else{//if it is a new item set quantity to one
                shoppingList.put(item,1);
            }
            tx.updateStock(id, item.stock-1);//update stock in db
        }
        else{
            System.out.println("This item is out of stock.");
        }
    }
    public void Receipt(HashMap<Item, Integer> shoppingList){
        String format = ("%d") + ("%s") + ("%-" + maxNameSize+1 + "s") +("%s") +("%g") +"\n";// this makes the receipt format
        for (Item item:shoppingList.keySet()) {//loop through every item bought
            //System.out.println("this is broken");
            System.out.printf(format,shoppingList.get(item),"*",item.getName(),"£",(float)item.getSell_price());
            totalPrice=totalPrice +item.getSell_price();
        }
        System.out.println("Your total is: £" + totalPrice);
        System.out.println("Please enter how much money was given to calculate change: ");
        moneyGiven = Menu.doubleInput();//asks how much money was given
        // this needs to check if the money is enough if not ask for more

        if(moneyGiven < totalPrice){
            System.out.println("Your price is: £" + totalPrice + " Please give the correct amount: ");
            moneyGiven = Menu.doubleInput();//asks how much money was given
            System.out.println("You have paid: £" + moneyGiven);
            changeDue = moneyGiven -totalPrice;//calculate change
            System.out.println("Your change is: £" + changeDue);
        } else if(moneyGiven == totalPrice){
            System.out.println("You have paid: £" + moneyGiven);
            //changeDue = moneyGiven -totalPrice;//calculate change
            System.out.println("Your change is: £0" + changeDue);
        }else {
            System.out.println("You have paid: £" + moneyGiven);
            changeDue = moneyGiven -totalPrice;//calculate change
            System.out.println("Your change is: £" + changeDue);
        }

        System.out.println("Small business");// this can be any business name


    }




}
