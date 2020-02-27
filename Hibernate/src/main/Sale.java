package main;

import java.util.HashMap;

public class Sale {
    HashMap<Item, Integer> shoppingList = new HashMap();
    int totalPrice;
    int moneyGiven;
    int changeDue;
    static int maxNameSize = 0;// this makes formatting the recipet a little easier.
    public static void main(String[] args){

    }
    public void sale(){
        while(true){
            if (Menu.intInput() ==1){
                String name = Menu.itemNameEnter();
                addToSale(name);
            }
            else if(Menu.intInput() ==2 ){
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
        moneyGiven = Menu.intInput();//asks how much money was given
        changeDue = moneyGiven -totalPrice;//calculate change
        System.out.println("small buissness");// this can be any buissness name
        String format = ("%d") + ("%s") + ("%-" + maxNameSize + "s") +("%s") +("%h");// this makes the recipt format
        for (Item item:shoppingList.keySet()) {//loop through every item bought
            System.out.printf(format,shoppingList.get(item),"*",item.getName(),"£",item.getSell_price());

        }
        System.out.println(totalPrice);
    }
}