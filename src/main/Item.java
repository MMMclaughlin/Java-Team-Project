package main;

import javax.persistence.*;

/**
 * Creates the Item table in the database, using Hibernate.
 * This creates each column in the table, and automatically generates a
 * unique ID for every item.
 *
 */
@Entity (name = "Item")// this lets hibernate know that this is an entity in the database
public class Item {
    //idpull ,name,category,perishable,cost,stock,sell_price
    private int id;
    private String name;
    private String category;
    private int perishable;
    private double cost;
    private int stock;
    private double sell_price;

    public Item(String name, String category, int perishable, double cost, int stock, double sell_price) {
        //this.id = id;
        this.name = name;
        this.category = category;
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

    public Item() {

    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nCategory: " + category + "\nPerishable: " + perishable +
                "\nCost: " + cost + "\nstock: " + stock + "\nPrice: " + sell_price;
    }

    @Id// this is the id value which will auto generate when we create a new Item
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)//sets parameters for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column (name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column (name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column (name = "perishable")
    public int getPerishable() {
        return perishable;
    }

    public void setPerishable(int perishable) {
        this.perishable = perishable;
    }

    @Basic
    @Column (name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Column (name = "stock")
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Basic
    @Column (name = "sell_price")
    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }
}