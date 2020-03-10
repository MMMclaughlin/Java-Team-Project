package main;
import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jtests {

    // this is a file for running a specific data set or queries and can be edited freely.
    public static void main(String[] args){
        Jtests test = new Jtests();
        test.queryTest();
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
    @Test
    @DisplayName("Test -> reciptTest")
    public void receiptTest(){
        Sale sale = new Sale();
        sale.addToSale(1);;
    }
    @Test
    @DisplayName("Test -> queryTest")
    public void queryTest(){
        Session session = HibernateUtil.getSessionFactory().openSession(); // Creates a Hibernate session
        session.beginTransaction();// begin transaction
        Item queryItem = session.createQuery("FROM Item", Item.class).setMaxResults(1).getResultList().get(0);
        Item item = new Transaction().findItem(queryItem.getId());
        System.out.println(item);
        System.out.println(queryItem);
        assertEquals(item.getId(),queryItem.getId());//this compares that the two queries get the same Item with the same id and therefore find item is sucessful


    }
    @Test
    @DisplayName("Test -> updateStockTest")
    public void updateTests(){
        Item tx = new Transaction().findItem(1);
        new Transaction().updateStock(tx.getId(),tx.getStock()+1);
        Item tx2 = new Transaction().findItem(1);
        assertEquals(tx.getStock(),tx2.getStock()-1);


    }
    @Test
    @DisplayName("Test -> deleteTest")
    public void deleteTests(){
        Item tx = new Transaction().findItem(1);
        new Transaction().deleteItem(tx.getId());
        Item tx2 = new Transaction().findItem(1);
        assertEquals(tx2,null);

    }
    @Test
    @DisplayName("Test -> InsertTest")
    public void insertTest(){
        Item item = new Item("test","test",1,100.00,10,120.00);
        assertEquals(new Transaction().findItem(item.getId()),null);
        new Transaction().insertItem(item);
        assertEquals(new Transaction().findItem(item.getId()).getId(),item.getId());
        new Transaction().deleteItem(item.getId());
    }
    @Test
    @DisplayName("Test -> AddToSaleTest")
    public void addToSaleTest(){
        Sale sale = new Sale();
        Item item = new Item("test","test",1,100.00,10,120.00);
        sale.addToSale(item.getId());// this shouldnt add as the item is not in the database yet
        assertEquals(Sale.shoppingList.size(),0);
        new Transaction().insertItem(item);
        sale.addToSale(item.getId());// this shouldnt add as the item is not in the database yet
        assertEquals(Sale.shoppingList.size(),1);

    }
}