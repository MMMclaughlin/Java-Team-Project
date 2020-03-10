package main;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * Builds a SessionFactory to use with Hibernate.
     *
     * @return the session factory
     */
    private static SessionFactory buildSessionFactory() {

        try {

            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gets the current session factory.
     *
     * @return the active session factory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Closes the session factory when finished.
     */
    public static void shutdown(){
        getSessionFactory().close();
    }
}
