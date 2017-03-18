package de.softaz.pizzabesteller.web.backend.order;

import de.softaz.pizzabesteller.web.backend.ResponseError;
import de.softaz.pizzabesteller.web.backend.user.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

import static de.softaz.pizzabesteller.web.backend.JsonUtil.json;
import static de.softaz.pizzabesteller.web.backend.JsonUtil.toJson;
import static spark.Spark.*;

/**
 * Created by pike-perch on 18.03.2017.
 */
public class OrderController {

    public OrderController(final OrderService orderService, final UserService userService) {
        get("/orders", (req, res) -> orderService.getAllOrders(), json());
        get("/orders/:id", (req, res) -> {
            try {
                return orderService.getOrder(new Long(req.params(":id"))).orElseGet(() -> {
                    res.status(400);
                    return new ResponseError("No order with id '%s' found", req.params(":id"));
                });
            } catch (NumberFormatException e) {
                res.status(400);
                return new ResponseError(e, "Given id '%s' is not a valid number", req.params(":id"));
            }
        }, json());
        post("/orders", (req, res) -> {
            try {
                return orderService.createOrder(
                        Optional.ofNullable(req.queryParams("delivery")),
                        Optional.ofNullable(req.queryParams("deadline")),
                        userService.getUser(req.queryParams("initiatorId")));
            } catch (Exception e) {
                res.status(400);
                return new ResponseError(e);
            }
        }, json());
        put("/orders/:id", (req, res) ->
                orderService.updateOrder(new Long(req.params(":id")),
                        Optional.ofNullable(req.queryParams("delivery")),
                        Optional.ofNullable(req.queryParams("deadline")),
                        Optional.ofNullable(req.queryParams("closed"))), json());
        put("/orders/:id/addItem", (req, res) ->
                orderService.addItem(new Long(req.params(":id")), userService.getUser(req.queryParams("costumerId")),
                        req.queryParams("itemNumber"), Double.parseDouble(req.queryParams("price"))), json());

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
        after((req, res) -> {
            res.type("application/json");
        });

    }
}
