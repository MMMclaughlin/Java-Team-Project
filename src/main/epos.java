package main;

import java.util.Scanner;

public class epos {
    public static void main(String[] args) {

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

        System.out.format("+----+------+----------+-------+%n");
        System.out.format("| ID | Name | Category | Price |%n");
        System.out.format("+----+------+----------+-------+%n");

        // f
        String leftAlignFormat = "| %-4s | %-6s | %-10s | %-7s |%n";

        System.out.format("+----+------+----------+-------+%n");

    }

    public void interactDB() {
        checkDB();
        // use checkDB to show DB
        // Use while to ask to add/remove/alter
        // Continue until all changes made.
    }
}
