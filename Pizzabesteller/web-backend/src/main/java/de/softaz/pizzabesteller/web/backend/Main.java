package de.softaz.pizzabesteller.web.backend;

import de.softaz.pizzabesteller.web.backend.order.OrderController;
import de.softaz.pizzabesteller.web.backend.order.OrderService;
import de.softaz.pizzabesteller.web.backend.user.UserController;
import de.softaz.pizzabesteller.web.backend.user.UserService;

/**
 * Created by pike-perch on 17.03.2017.
 */
public class Main {
    public static void main(String args[]) {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        UserController userController = new UserController(userService);
        OrderController orderController = new OrderController(orderService, userService);
    }
}
