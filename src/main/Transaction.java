package main;

import org.hibernate.*;
import javax.persistence.Entity;
public class Transaction {
    //establish database connection here

    public static void main(String[] args){

    }

    public static Item findItem(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();//creates a session
        session.beginTransaction();// begin transaction
        Item item = (Item)session.get(Item.class,id);//looks up item
        session.close();//closes current session
        return item;//return found item or null if item wasnt found
    }

    public static void updateStock(int id, int newStock){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession(); // Create a session
            session.beginTransaction(); // Begin the transaction
            Item item = session.get(Item.class, id); // Gets the item from the database
            item.setStock(newStock); // Sets the item stock level to the new stock
            session.update(item); // Updates the item's stock in the database
            session.close();
        }
        catch(HibernateException e)
        {
            e.printStackTrace();
        }
    }
    public static void deleteItem(Item item){

    }
}