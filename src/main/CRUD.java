package main;

public interface CRUD {
    /**
     * Queries a given item's ID from the database
     * and returns the item found.
     * @param id the item's ID
     * @return Item found in the database
     */
    Item findItem(int id);

    /** Queries a given ID and updates the
     * corresponding item's stock to the new value
     *
     * @param id the item's ID
     * @param newStock the new stock level of the item
     */


    void updateStock(int id, int newStock);

    /** Finds an item from the database using its ID,
     * and removes that item.
     *
     * @param id the item's ID
     */
    void deleteItem(int id);

    /** Insert a new given Item object into the database.
     *
     * @param item the item to add to the database
     */
    void insertItem(Item item);


}

