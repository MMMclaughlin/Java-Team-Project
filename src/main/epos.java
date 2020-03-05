package main;

import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Scanner;

public class epos {
    public static void main(String[] args) {
        epos myEPOS = new epos();
        myEPOS.interactDB();
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
                // Displays database to user.
                checkDB();
                newSale();
                return;
            case 2:
                System.out.println("VIEW DB");
                checkDB();
                return;
            case 3:
                System.out.println("ALTER DB");
                interactDB();
                return;
        }
    }

    public void newSale() {
        // Creates new sale object and calls constructor.
        Sale sale = new Sale();
        sale.sale();
    }

    public void checkDB() {
        // Read all records from DB
        // Formats data into ASCII table
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Item order by id ");
        List results = query.getResultList();
        session.getTransaction().commit();

        System.out.format("+-----+--------------------------------+-----------------+------------+------------+" +
                "----------+------------+%n");
        System.out.format("| ID  | Name                           | Category        | Perishable | Cost       |" +
                " Stock    | Price      |%n");
        System.out.format("+-----+--------------------------------+-----------------+------------+------------+" +
                "----------+------------+%n");

        String leftAlignFormat = "| %-3s | %-30s | %-15s | %-10s | %-10s | %-8s | %-10s |%n";

        for (Object i : results) {
            Item thisItem = (Item) i;
            System.out.format(leftAlignFormat, thisItem.getId(), thisItem.getName(), thisItem.getCategory(),
                    thisItem.getPerishable(), thisItem.getCost(), thisItem.getStock(), thisItem.getSell_price());
        }

        System.out.format("+-----+--------------------------------+-----------------+------------+------------+" +
                "----------+------------+%n");

    }

    public void interactDB() {
        System.out.println("Database in Current form:");
        checkDB();

        System.out.println("Choose 1 to 5, to select option: \n" +
                "1 - Locate item by ID \n" +
                "2 - Update stock \n" +
                "3 - Delete item \n" +
                "4 - Insert new item \n" +
                "5 - Exit");

        Scanner input = new Scanner(System.in);
        String entered = input.nextLine();

        int choice = 1;

        try {
            choice = Integer.parseInt(entered);
        } catch (NumberFormatException e) {
            System.out.println("### Choice must be an integer. Default to locate item by ID. ###");
        }

        if (choice > 5 || choice < 1) {
            System.out.println("### Choice must be between 1 and 5. Default to locate item by ID. ###");
        }

        Transaction thisTransaction = new Transaction();
        switch (choice) {
            case 1:
                System.out.println("FIND ITEM BY ID:");
                System.out.println("Enter ID to locate:");
                Scanner case1Input = new Scanner(System.in);
                String IDChoice = case1Input.nextLine();

                int searchID = 1;

                try {
                    searchID = Integer.parseInt(IDChoice);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be an integer. Default to ID = 1. ###");
                }

                System.out.println(thisTransaction.findItem(searchID).toString());
                return;

            case 2:
                System.out.println("UPDATE STOCK");
                System.out.println("Enter ID of item to amend stock of:");
                Scanner case2Input = new Scanner(System.in);
                String case2Choice = case2Input.nextLine();

                int case2ID = 1;

                try {
                    case2ID = Integer.parseInt(case2Choice);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be an integer. Default to ID = 1. ###");
                }

                System.out.println("Enter new stock value:");
                String case2EnteredStock = case2Input.nextLine();

                int case2NewStock = 0;

                try {
                    case2NewStock = Integer.parseInt(case2EnteredStock);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be an integer. Default to 0. ###");
                }

                thisTransaction.updateStock(case2ID, case2NewStock);
                return;

            case 3:
                
        }

        // use checkDB to show DB
        // Use while to ask to add/remove/alter
        // Continue until all changes made.
    }
}
