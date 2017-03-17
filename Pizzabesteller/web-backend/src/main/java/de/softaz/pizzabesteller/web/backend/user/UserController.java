package de.softaz.pizzabesteller.web.backend.user;

import de.softaz.pizzabesteller.web.backend.ResponseError;
import de.softaz.pizzabesteller.web.backend.User;

import static de.softaz.pizzabesteller.web.backend.JsonUtil.json;
import static de.softaz.pizzabesteller.web.backend.JsonUtil.toJson;
import static spark.Spark.*;


/**
 * Created by pike-perch on 17.03.2017.
 */
public class UserController {
    public UserController(final UserService userService) {
        get("/users", (req, res) -> userService.getAllUsers(), json());
        get("/users/:id", (req, res) -> {
            try {
                Long id = new Long(req.params(":id"));
                User user = userService.getUser(id);
                if (user != null) {
                    return user;
                }
                res.status(400);
                return new ResponseError("No user with id '%s' found", id.toString());
            } catch (NumberFormatException e) {
                res.status(400);
                return new ResponseError(e, "Given id '%s' is not a valid number", req.params(":id"));
            }
        }, json());
        post("/users", (req, res) -> userService.createUser(req.queryParams("name")), json());
        put("/users/:id", (req, res) -> {
            try {
                Long id = new Long(req.params(":id"));
                return userService.updateUser(id, req.queryParams("name"));
            } catch (NumberFormatException e) {
                return new ResponseError(e, "Given id '%s' is not a valid number", req.params(":id"));
            }
        }, json());
        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
        after((req, res) -> {
            res.type("application/json");
        });

    }
}
