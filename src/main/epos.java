package main;

import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class epos {
    public static void main(String[] args) {
        // Creates epos instance and calls choice.
        epos myEPOS = new epos();
        myEPOS.choice();

        // Continues until user has completed all actions.
        boolean toContinue = true;
        while (toContinue) {
            Scanner continueScanner = new Scanner(System.in);
            System.out.println("Do you wish to perform another action? Enter only Y or N.");
            String entered = continueScanner.nextLine().toUpperCase();

            if (entered.equals("Y")) {
                // Calls choice when the user wants to continue.
                myEPOS.choice();
            } else {
                toContinue = false;
                // Exits when user is finished.
                System.out.println("Exit");
                System.exit(1);
            }
        }
    }

    // Gives user options then calls appropriate methods.
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
                break;
            case 2:
                System.out.println("VIEW DB");
                checkDB();
                break;
            case 3:
                System.out.println("ALTER DB");
                interactDB();
                break;
        }
    }

    public void newSale() {
        // Creates new saleStart object and calls constructor.
        Sale sale = new Sale();
        sale.saleStart();
    }

    public void checkDB() {
        // Read all records from DB
        // Formats data into ASCII table
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Gets all items from DB.
        Query query = session.createQuery("FROM Item order by id ");
        List results = query.getResultList();
        session.getTransaction().commit();
        session.close();

        // Formats head of table.
        System.out.format("+-----+--------------------------------+-----------------+------------+------------+" +
                "----------+------------+%n");
        System.out.format("| ID  | Name                           | Category        | Perishable | Cost       |" +
                " Stock    | Price      |%n");
        System.out.format("+-----+--------------------------------+-----------------+------------+------------+" +
                "----------+------------+%n");

        // Format for body of table.
        String leftAlignFormat = "| %-3s | %-30s | %-15s | %-10s | %-10s | %-8s | %-10s |%n";

        // Iterates through items and populates body of the table.
        for (Object i : results) {
            Item thisItem = (Item) i;
            System.out.format(leftAlignFormat, thisItem.getId(), thisItem.getName(), thisItem.getCategory(),
                    thisItem.getPerishable(), thisItem.getCost(), thisItem.getStock(), thisItem.getSell_price());
        }

        // Foot of the table.
        System.out.format("+-----+--------------------------------+-----------------+------------+------------+" +
                "----------+------------+%n");

    }

    public void interactDB() {
        // Displays DB to user.
        System.out.println("Database in Current form:");
        checkDB();

        // Gives user their options.
        System.out.println("Choose 1 to 5, to select option: \n" +
                "1 - Locate item by ID \n" +
                "2 - Update stock \n" +
                "3 - Delete item \n" +
                "4 - Insert new item \n" +
                "5 - Exit");

        // Take's user input.
        Scanner input = new Scanner(System.in);
        String entered = input.nextLine();

        // Default choice.
        int choice = 1;

        // Parses to int, throws error if user hasn't entered a number.
        try {
            choice = Integer.parseInt(entered);
        } catch (NumberFormatException e) {
            System.out.println("### Choice must be an integer. Default to locate item by ID. ###");
        }

        // Ensures choice is in appropriate range.
        if (choice > 5 || choice < 1) {
            System.out.println("### Choice must be between 1 and 5. Default to locate item by ID. ###");
        }

        // Performs appropriate acction based on user's choice.
        Transaction thisTransaction = new Transaction();
        switch (choice) {
            // Locate an item.
            case 1:
                System.out.println("FIND ITEM BY ID:");
                System.out.println("Enter ID to locate:");
                Scanner case1Input = new Scanner(System.in);
                String IDChoice = case1Input.nextLine();

                // Default search ID
                int searchID = 1;

                // Takes ID from user and checks it's validity.
                try {
                    searchID = Integer.parseInt(IDChoice);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be an integer. Default to ID = 1. ###");
                }

                // Shows user the information, catches if record doesn't exist.
                try {
                    System.out.println(thisTransaction.findItem(searchID).toString());
                } catch (NullPointerException e) {
                    System.out.println("### Invalid ID Entered. ###");
                }

                break;

            case 2:
                // Update stock.
                System.out.println("UPDATE STOCK");
                System.out.println("Enter ID of item to amend stock of:");
                Scanner case2Input = new Scanner(System.in);
                String case2Choice = case2Input.nextLine();

                // Default ID.
                int case2ID = 1;

                //Checks validity of entered value.
                try {
                    case2ID = Integer.parseInt(case2Choice);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be an integer. Default to ID = 1. ###");
                }

                System.out.println("Enter new stock value:");
                String case2EnteredStock = case2Input.nextLine();

                // Default new stock value.
                int case2NewStock = 0;

                // Parse to integer, catches if user doesn't enter an integer.
                try {
                    case2NewStock = Integer.parseInt(case2EnteredStock);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be an integer. Default to 0. ###");
                } catch (NullPointerException e) {
                    System.out.println("### Invalid ID entered. ###");
                }

                // Performs transaction.
                thisTransaction.updateStock(case2ID, case2NewStock);
                break;

            case 3:
                // Delete a record.
                System.out.println("DELETE ITEM");
                System.out.println("Enter ID of item to delete:");
                Scanner case3Input = new Scanner(System.in);
                String case3Choice = case3Input.nextLine();

                // Performs checks on entered value.
                try {
                    int case3ID = Integer.parseInt(case3Choice);
                    // Carries out transaction.
                    thisTransaction.deleteItem(case3ID);
                } catch (NumberFormatException e) {
                    System.out.println("### Choice must be an integer. Exit without change. ###");
                    break;
                } catch (NullPointerException e) {
                    System.out.println("### Invalid ID entered. ###");
                }
                break;

            case 4:
                // Insert new item.
                System.out.println("INSERT NEW ITEM");
                Scanner case4Input = new Scanner(System.in);

                // Takes all values for new item.
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

                // Checks validity of all input values.
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

                // Creates item object, outputs the object then adds to DB.
                Item thisItem = new Item(case4Name, case4Category, intPerishable, doubleCost, IntStock, doublePrice);
                System.out.println(thisItem.toString());
                thisTransaction.insertItem(thisItem);

            case 5:
                System.exit(1);
        }
    }
}
