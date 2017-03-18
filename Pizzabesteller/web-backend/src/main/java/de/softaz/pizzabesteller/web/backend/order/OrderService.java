package de.softaz.pizzabesteller.web.backend.order;

import de.softaz.pizzabesteller.web.backend.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by pike-perch on 18.03.2017.
 */
public class OrderService {
    List<Order> orders = new ArrayList<>();

    public List<Order> getAllOrders() {
        return orders;
    }

    public List<Order> getOpenOrders() {
        return orders.stream().filter(o -> !o.isClosed()).collect(Collectors.toList());
    }

    public List<Order> getClosedOrders() {
        return orders.stream().filter(Order::isClosed).collect(Collectors.toList());
    }

    public Optional getOrder(long id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst();
    }

    public Order createOrder(Optional<String> delivery, Optional<String> deadline, User initiator) {
        if (!delivery.isPresent() || !deadline.isPresent()) {
            throw new IllegalArgumentException("delivery or deadline not set");
        }
        long nextId = orders.stream().map(Order::getId).sorted((o1, o2) -> Long.compare(o2, o1)).findFirst().orElse(0L) + 1;
        Order newOrder = new Order(LocalDateTime.parse(delivery.get()), LocalDateTime.parse(deadline.get()), initiator, nextId);
        orders.add(newOrder);
        return newOrder;
    }

    public Order updateOrder(long id, Optional<String> delivery, Optional<String> deadline, Optional<String> closed) {
        Order toBeUpdated = (Order) getOrder(id).get();
        delivery.ifPresent(e -> toBeUpdated.setDelivery(LocalDateTime.parse(e)));
        deadline.ifPresent(e -> toBeUpdated.setDeadline(LocalDateTime.parse(e)));
        closed.ifPresent(e -> toBeUpdated.setClosed(Boolean.parseBoolean(e)));
        return toBeUpdated;
    }

    public Order addItem(long orderId, User costumer, String itemNumber, double price) {
        Order order = (Order) getOrder(orderId).get();
        order.getItems().add(new Item(itemNumber, costumer, price, false));
        return order;
    }

    public List<Item> getItemsFromOrder(long orderId) {
        return ((Order) getOrder(orderId).get()).getItems();
    }

    public List<Item> getItemsFromOrderForCostumer(long orderId, long costumerId) {
        return ((Order) getOrder(orderId).get()).getItems().stream().filter(i -> i.getCostumer().getId() == costumerId).collect(Collectors.toList());
    }

}
