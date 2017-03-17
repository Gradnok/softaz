package de.softaz.pizzabesteller.web.backend;

/**
 * Created by pike-perch on 17.03.2017.
 */
public class Main {
    public static void main(String args[]) {
        new UserController(new UserService());
    }
}
