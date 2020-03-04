package main;

import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Scanner;

public class epos {
    public static void main(String[] args) {
        epos myEPOS = new epos();
        myEPOS.checkDB();
    }

    public void choice() {
        // Display choices to user.
        System.out.println("Choose one of the following options: \n" +
                "1 - Create new sale \n" +
                "2 - View database in current state \n" +
                "3 - Change database values");
        // Take input from user.
        Scanner choice = new Scanner(System.in);
        String enteredChoice = choice.nextLine();

        // Default and starting choice.
        int finalChoice = 2;

        // Parse to integer ensures integer entered, if not int use choice 2.
        try {
            finalChoice = Integer.parseInt(enteredChoice);
        } catch (NumberFormatException e) {
            System.out.println("### ERROR - Choice must be an integer. Default to view database. ###");

        }

        // Check choice is within range.
        if (finalChoice > 3 || finalChoice < 1) {
            System.out.println("### ERROR - Choice must be 1, 2 or 3. Default to 2, view database. ###");
            finalChoice = 2;
        }

        // Calls appropriate function.
        switch (finalChoice){
            case 1:
                System.out.println("NEW SALE");
                newSale();
            case 2:
                System.out.println("VIEW DB");
                checkDB();
            case 3:
                System.out.println("ALTER DB");
                interactDB();
        }
    }

    public void newSale() {
        // Displays database to user.
        checkDB();
        // Creates new sale object and calls constructor.
        Sale sale = new Sale();
        sale.sale();
    }

    public void checkDB() {
        // Read all records from DB
        // Formats data into ASCII table
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Item where stock != 0 order by category ");
        List results = query.getResultList();
        session.getTransaction().commit();

        System.out.format("+-----+--------------------------------+-----------------+------------+%n");
        System.out.format("| ID  | Name                           | Category        | Price      |%n");
        System.out.format("+-----+--------------------------------+-----------------+------------+%n");

        String leftAlignFormat = "| %-3s | %-30s | %-15s | %-10s |%n";

        for (Object i : results) {
            Item thisItem = (Item) i;
            System.out.format(leftAlignFormat, thisItem.getId(), thisItem.getName(), thisItem.getCategory(),
                    thisItem.getSell_price());
        }

        System.out.format("+-----+--------------------------------+-----------------+------------+%n");

    }

    public void interactDB() {
        checkDB();
        // use checkDB to show DB
        // Use while to ask to add/remove/alter
        // Continue until all changes made.
    }
}
