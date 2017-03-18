package de.softaz.pizzabesteller.web.backend.order;

import de.softaz.pizzabesteller.web.backend.user.User;

/**
 * Created by pike-perch on 18.03.2017.
 */
public class Item {
    private String itemNumber;
    private User costumer;
    private double price;
    private boolean amountPaid;


    public Item(String itemNumber, User costumer, double price, boolean amountPaid) {
        this.itemNumber = itemNumber;
        this.costumer = costumer;
        this.price = price;
        this.amountPaid = amountPaid;
    }

    public Item(){

    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public User getCostumer() {
        return costumer;
    }

    public void setCostumer(User costumer) {
        this.costumer = costumer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(boolean amountPaid) {
        this.amountPaid = amountPaid;
    }
}
