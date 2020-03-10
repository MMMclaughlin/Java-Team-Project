package main;

import org.hibernate.*;
public class Transaction implements CRUD{
    //establish database connection here
    Session session = HibernateUtil.getSessionFactory().openSession(); // Creates a Hibernate session

    public static void main(String[] args){

        }

    /** Finds and returns an item from the Item table in the database, which
     * corresponds to the inputted ID.
     *
      * @param id - an integer value, used as the item's ID in the Item table
     * @return the corresponding item from the database
     */
    @Override
    public Item findItem(int id) {

        Session sessionLocal = HibernateUtil.getSessionFactory().openSession();
        try {
            sessionLocal.beginTransaction();// begin transaction
            Item item = sessionLocal.get(Item.class, id);//looks up item
            return item;//return found item or null if item wasn't found
        }
        catch (HibernateException e)
        {
            if (sessionLocal!=null) {
                sessionLocal.getTransaction().rollback();
                e.printStackTrace();
            }
        }
        finally {
            if (sessionLocal != null) {
                sessionLocal.close();
            }
        }
        return null;
    }

    /** Updates the stock level of a given item.
     * This uses a Hibernate session to get the item based on its ID,
     * and then updates its stock in the database, using the user-defined
     * integer value.
     *
     * @param id - the ID of the desired item in the Item table
     * @param newStock the new stock level of the item
     */
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

    /**
     * Deletes an item from the database using its ID.
     *
     * @param id the item's ID in the Item table in the database
     */
    @Override
    public void deleteItem(int id) {
        try
        {
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

    /**
     * Adds a new item to the database table.
     *
     * @param item the item to be added to the database
     */
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

