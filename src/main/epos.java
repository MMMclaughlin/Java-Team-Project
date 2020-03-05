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
            case 2:
                System.out.println("VIEW DB");
                checkDB();
            case 3:
                System.out.println("ALTER DB");
                interactDB();
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
                break;

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
                break;

            case 3:
                System.out.println("DELETE ITEM");
                System.out.println("Enter ID of item to delete:");
                Scanner case3Input = new Scanner(System.in);
                String case3Choice = case3Input.nextLine();

                try {
                    int case3ID = Integer.parseInt(case3Choice);
                    thisTransaction.deleteItem(case3ID);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be an integer. Exit without change. ###");
                    break;
                }
                break;

            case 4:
                System.out.println("INSERT NEW ITEM");
                Scanner case4Input = new Scanner(System.in);

                System.out.println("Enter Name of new item:");
                String case4Name = case4Input.nextLine();
                System.out.println("Enter category of new item:");
                String case4Category = case4Input.nextLine();
                System.out.println("Enter 1 if perishable 0 if not for new item:");
                String case4Perishable = case4Input.nextLine();
                System.out.println("Enter cost of new item:");
                String case4Cost = case4Input.nextLine();
                System.out.println("Enter stock count of new item:");
                String case4Stock = case4Input.nextLine();
                System.out.println("Enter sell price of new item:");
                String case4Price = case4Input.nextLine();

                int intPerishable = 1;
                try {
                    intPerishable = Integer.parseInt(case4Perishable);

                    if (intPerishable > 1 || intPerishable < 0) {
                        System.out.println("### Must be 1 or 0. Default to 1. ###");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be true or false. Default to true. ###");
                }

                double doubleCost = 0;
                try {
                    doubleCost = Double.parseDouble(case4Cost);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be decimal number. Default to 0. ###");
                }

                int IntStock = 0;
                try {
                    IntStock = Integer.parseInt(case4Stock);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be integer. Default to 0. ###");
                }

                double doublePrice = 0;
                try {
                    doublePrice = Double.parseDouble(case4Price);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be decimal number. Default to 0. ###");
                }

                Item thisItem = new Item(case4Name, case4Category, intPerishable, doubleCost, IntStock, doublePrice);
                System.out.println(thisItem.toString());
                thisTransaction.insertItem(thisItem);
        }

        // use checkDB to show DB
        // Use while to ask to add/remove/alter
        // Continue until all changes made.
    }
}
