package main;

import java.util.HashMap;

public class test{
    public static void main(String[] args){
        Item item1 = new Item(0,"test","test",true,11.00,12,13.00);
        Item item2 = new Item(1,"test1","test1",true,12.00,12,14.00);
        HashMap<Item, Integer> testMap= new HashMap<>();
        Sale sale = new Sale();
        sale.shoppingList.put(item1,1);
        sale.shoppingList.put(item2,1);
        sale.sale();
    }
}