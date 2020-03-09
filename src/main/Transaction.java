package main;

import org.hibernate.*;
public class Transaction implements CRUD{
    //establish database connection here
    Session session = HibernateUtil.getSessionFactory().openSession(); // Creates a Hibernate session

    public static void main(String[] args){

        }

    @Override
    public Item findItem(int id) {
        try {
            session.beginTransaction();// begin transaction
            Item item = session.get(Item.class, id);//looks up item
            return item;//return found item or null if item wasn't found
        }
        catch (HibernateException e)
        {
            if (session!=null) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public void updateStock(int id, int newStock) {
        try
        {
            session.beginTransaction(); // Begin the transaction
            Item item = session.get(Item.class, id); // Gets the item from the database
            item.setStock(newStock); // Sets the item stock level to the new stock
            session.update(item); // Updates the item's stock in the database
            session.getTransaction().commit(); // Commit transaction
        }
        catch (HibernateException e)
        {
            if (session!=null) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void deleteItem(int id) {
        try
        {
            //Session session = HibernateUtil.getSessionFactory().openSession(); // Create a session
            session.beginTransaction(); // Begin the transaction
            Item item = session.get(Item.class, id); // Gets the item from the database
            session.delete(item); // Deletes the specific item from the database
            session.getTransaction().commit(); // Committing the transaction
        }
        catch (HibernateException e)
        {
            if (session!=null) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void insertItem(Item item) {

        try {
            session.beginTransaction(); // Begin the transaction
            session.save(item);
            session.getTransaction().commit(); // Committing the transaction
        }
        catch (HibernateException e)
        {
            if (session!=null) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

