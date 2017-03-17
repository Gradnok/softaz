package de.softaz.pizzabesteller.web.backend;

/**
 * Created by pike-perch on 17.03.2017.
 */
public class ResponseError {
    private String message;

    public ResponseError(String message, String... args) {
        this.message = String.format(message, args);
    }

    public ResponseError(Exception e, String message, String... args) {

        this.message = String.format(message, args);
        this.message += e.getMessage();
    }

    public ResponseError(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}
