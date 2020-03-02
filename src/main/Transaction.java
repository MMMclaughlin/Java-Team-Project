package main;

import org.hibernate.*;

public class Transaction {
    static Session session = HibernateUtil.getSessionFactory().openSession();
    //establish database connection here
    public static void main(String[] args){

    }
    public static Item findItem(String id){
        //find item by name or id? might want to ask specification requirement here
        session.beginTransaction();
        Item item = session.get(Item.class,id);
        System.out.println(item);
        System.out.println("made it");
        return item;
    }
    public static void updateEntity(Item item){

    }
    public static void deleteItem(Item item){

    }
}