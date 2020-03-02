package main;

public class epos {
    public static void main(String[] args) {

    }

    public void choice() {
        // User choice:
        // 1 - New sale
        // 2 - Check DB (output as ASCII table)
        // 3 - Add/remove/update DB
    }

    public void newSale() {
        // Call Check DB function to show user what's available
        // Create new sale object
        // Call constructor
    }

    public void checkDB() {
        // Read all records from DB
        // Formats data into ASCII table

        System.out.format("+----+------+----------+-------+%n");
        System.out.format("| ID | Name | Category | Price |%n");
        System.out.format("+----+------+----------+-------+%n");

        // for
        String leftAlignFormat = "| %-4s | %-6s | %-10s | %-7s |%n";

        System.out.format("+----+------+----------+-------+%n");

    }

    public void interactDB() {
        // use checkDB to show DB
        // Use while to ask to add/remove/alter
        // Continue until all changes made.
    }
}
