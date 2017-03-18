package de.softaz.pizzabesteller.web.backend.order;

import de.softaz.pizzabesteller.web.backend.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pike-perch on 18.03.2017.
 */
public class Order {
    private boolean closed;
    private LocalDateTime delivery;
    private LocalDateTime deadline;
    private User initiator;
    private List<Item> items = new ArrayList<>();

    private long id;

    public Order(){

    }

    public Order(LocalDateTime delivery, LocalDateTime deadline, User initiator, long id) {
        this.delivery = delivery;
        this.deadline = deadline;
        this.initiator = initiator;
        this.id = id;
    }


    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public User getInitiator() {
        return initiator;
    }

    public void setInitiator(User initiator) {
        this.initiator = initiator;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LocalDateTime getDelivery() {
        return delivery;
    }

    public void setDelivery(LocalDateTime delivery) {
        this.delivery = delivery;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
