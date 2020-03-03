package main;

import java.util.HashMap;

public class Sale {
    static HashMap<Item, Integer> shoppingList = new HashMap();
    double totalPrice;
    double moneyGiven;
    double changeDue;
    static int maxNameSize = 5;// this makes formatting the recipet a little easier.
    public static void main(String[] args){

    }
    public void sale(){
        while(true){
            System.out.println("please enter a number 1: to enter another item 2: to exit");//user input
            if (Menu.intInput() ==1){
                System.out.println("enter id for the item");//adding next item
                int id = Menu.intInput();//takes input of next id
                addToSale(id);//adding new id to sale
            }
            else {//sale finished creating recipt
                System.out.println("creating recipt");
                Recipt(shoppingList);//pass shopping list into recipt
                System.out.println("Sale complete");
                break;
            }
        }
    }
    public static void addToSale(int id){

        Item item = Transaction.findItem(id);
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
            Transaction.updateStock(id, item.stock-1);//update stock in db
        }
        else{
            System.out.println("This item is out of stock.");
        }
        }
    public void Recipt(HashMap<Item, Integer> shoppingList){
        System.out.println("please enter how much money was given to calculate change ");
        moneyGiven = Menu.intInput();//asks how much money was given
        // this needs to check if the money is enough if not ask for more
        System.out.println("small buissness");// this can be any buissness name
        String format = ("%d") + ("%s") + ("%-" + maxNameSize+1 + "s") +("%s") +("%g") +"\n";// this makes the recipt format
        for (Item item:shoppingList.keySet()) {//loop through every item bought

            System.out.printf(format,shoppingList.get(item),"*",item.getName(),"£",(float)item.getSell_price());
            totalPrice=totalPrice +item.getSell_price();
        }

        changeDue = moneyGiven -totalPrice;//calculate change
        System.out.println(totalPrice);
    }
}