package main;

import javax.persistence.*;
@Entity (name = "Item")// this lets hibernate know that this is an entity in the database
public class Item {
    //idpull ,name,category,perishable,cost,stock,sell_price
    @Id// this is the id value which will auto generate when we create a new Item
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)//sets parameters for id
    private int id;
    @Column
    String name;
    @Column
    String category;
    @Column
    int perishable;
    @Column
    double cost;
    @Column
    int stock;
    @Column
    double sell_price;

    public Item(String name, String category, int perishable, double cost, int stock, double sell_price) {
        //this.id = id;
        this.name = name;
        this.category = category;
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

    public static void main(String[] args){

    }

    public Item() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPerishable() {
        return perishable;
    }

    public void setPerishable(int perishable) {
        this.perishable = perishable;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }
}