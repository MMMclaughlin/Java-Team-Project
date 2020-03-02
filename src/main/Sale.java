package main;

import java.util.HashMap;

public class Sale {
    HashMap<Item, Integer> shoppingList = new HashMap();
    double totalPrice;
    double moneyGiven;
    double changeDue;
    static int maxNameSize = 5;// this makes formatting the recipet a little easier.
    public static void main(String[] args){

    }
    public void sale(){
        while(true){
            System.out.println("please enter a number 1: to enter another item 2: to exit");
            if (Menu.intInput() ==1){
                String name = Menu.itemNameEnter();
                addToSale(name);
            }
            else {
                System.out.println("creating recipt");
                Recipt(shoppingList);
                System.out.println("Sale complete");
                break;
            }
        }
    }
    public static void addToSale(String itemName){
        /*perform query of entity from db here
         queriedItem = select * from itemTable where name = itemName// this needs to use the Item constructor

         if (shoppinglist.contains(querieditem)){
                shoppinglist.replace(querieditem, shoppinglist.get(querieditem) +1)
           }
          else{
          shoppinglist.put(queriedItem,1)
          }


         */
        if (itemName.length() > maxNameSize){
            maxNameSize = itemName.length();
        }
    }
    public void Recipt(HashMap<Item, Integer> shoppingList){
        System.out.println("please enter how much money was given to calculate change ");
        moneyGiven = Menu.intInput();//asks how much money was given

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