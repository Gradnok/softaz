package de.softaz.pizzabesteller.web.backend.user;

/**
 * Created by pike-perch on 17.03.2017.
 */
public class User {
    private String name;
    private long id;

    public User() {
    }

    public User(long id, String name) {

        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
