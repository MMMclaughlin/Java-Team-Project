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
        return item;//return found item or null if item wasn't found
    }

    public static void updateStock(int id, int newStock){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession(); // Create a session
            session.beginTransaction(); // Begin the transaction
            Item item = session.get(Item.class, id); // Gets the item from the database
            item.setStock(newStock); // Sets the item stock level to the new stock
            session.update(item); // Updates the item's stock in the database
            session.getTransaction().commit(); // Commit transaction
            session.close();
        }
        catch(HibernateException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void deleteItem(int id){
        try
        {
            Session session = HibernateUtil.getSessionFactory().openSession(); // Create a session
            session.beginTransaction(); // Begin the transaction
            Item item = session.get(Item.class, id); // Gets the item from the database
            session.delete(item); // Deletes the specific item from the database
            session.getTransaction().commit(); // Committing the transaction
            session.close();
        }
        catch(HibernateException e)
        {
            e.printStackTrace();
        }
    }
    public static void insertItem(Item item){
        Session session = HibernateUtil.getSessionFactory().openSession(); // Create a session
        session.beginTransaction(); // Begin the transaction
        session.save(item);
        session.getTransaction().commit(); // Committing the transaction
        session.close();
    }
}