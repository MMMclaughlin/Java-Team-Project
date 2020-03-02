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
    public static void updateEntity(Item item){

    }
    public static void deleteItem(Item item){

    }
}