package de.softaz.pizzabesteller.web.backend;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by pike-perch on 17.03.2017.
 */
public class JsonUtil {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json(){
        return JsonUtil::toJson;
    };
}
