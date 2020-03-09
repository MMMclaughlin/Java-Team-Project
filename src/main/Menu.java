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
        String enteredValue = input.nextLine();
        int menuChoice = 0;
        try{
            menuChoice = Integer.parseInt(enteredValue);
        } catch (NumberFormatException e) {
            System.out.println("### Only enter an integer. Default to 0. ###");
        }
        return menuChoice;
    }

    /** Takes user input from the console.
     *
     * @return the user's input as a double
     */
    public static double doubleInput() {
        Scanner doubleInput = new Scanner(System.in);
        String enteredDouble = doubleInput.nextLine();

        double doubleChoice = 0;
        try{
            doubleChoice = Double.parseDouble(enteredDouble);
        } catch (NumberFormatException e) {
            System.out.println("### Only enter a double. Default to 0. ###");
        }
        return doubleChoice;

    }

    public static String itemNameEnter(){
        // this takes and returns a string
        Scanner input = new Scanner(System.in);
        String itemName = input.nextLine();
        return itemName;
    }
}