package main;

public interface CRUD {
    /**
     * Query a given items id and return the object found
     * @param id
     * @return Item found in the database
     */
    Item findItem(int id);

    /** query a given id and update that items stock to givenn new value
     *
     * @param id
     * @param newStock
     */


    void updateStock(int id, int newStock);

    /** query an item using its id and then remove that item
     *
     * @param id
     */
    void deleteItem(int id);

    /** insert a new given Item object into the database
     *
     * @param item
     */
    void insertItem(Item item);


}

