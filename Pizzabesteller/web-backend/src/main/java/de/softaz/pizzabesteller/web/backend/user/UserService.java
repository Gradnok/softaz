package de.softaz.pizzabesteller.web.backend.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pike-perch on 17.03.2017.
 */
public class UserService {
    List<User> users = new ArrayList<User>(Arrays.asList(
            new User(1, "Andreas"),
            new User(2, "Christin"),
            new User(3, "Manuel"),
            new User(4, "Katrin")));

    public List<User> getAllUsers() {
        return users;
    }

    public User getUser(long id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().get();
    }

    public User createUser(String name) {
        long nextId = users.stream().sorted((u1, u2) -> Long.compare(u2.getId(), u1.getId())).findFirst().get().getId();
        User newUser = new User(nextId, name);
        users.add(newUser);
        return newUser;
    }

    public User updateUser(long id, String name) {
        User updatedUser = users.stream().filter(u -> u.getId() == id).findFirst().get();
        updatedUser.setName(name);
        return updatedUser;
    }

    public User getUser(String id) {
        try {
            return getUser(new Long(id));
        } catch (NumberFormatException e) {
            throw new NumberFormatException(String.format("Given userId '%s' is not a valid number", id));
        }
    }
}

