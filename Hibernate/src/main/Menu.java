package main;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args){
        
    }
    public static int intInput(){
        /*
        this is for taking a number and then choosing an option eg:
        enter 1 to buy an item
        enter 2 to update an item etc
         */
        Scanner input = new Scanner(System.in);
        int menuChoice = input.nextInt();
        return menuChoice;
    }
    public static String itemNameEnter(){
        // this takes and returns a strin
        Scanner input = new Scanner(System.in);
        String itemName = input.nextLine();
        return itemName;
    }
}