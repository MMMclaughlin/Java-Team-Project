package main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jtests {

    // this is a file for running a specific data set or queries and can be edited freely.
    public static void main(String[] args){

    }
    //Input Tests
    @Test
    @DisplayName("Test -> intInput")
    public void intInputTest(){
        int input = Menu.intInput();//give the expected value as the input
        int expected = 5;
        assertEquals(expected,input);
    }
    @Test
    @DisplayName("Test -> doubleInput")
    public void doubleInputTest(){
        double input = Menu.intInput();//give the expected value as the input
        double expected = 5.50;
        assertEquals(expected,input);
    }
    @Test
    @DisplayName("Test -> stringInput")
    public void stringInputTest(){
        String input = Menu.stringInput();
        String expected ="test";
        assertEquals(expected,input);
    }
    public void receiptTest(){
        Sale sale = new Sale();
        sale.addToSale(1);;
    }
}