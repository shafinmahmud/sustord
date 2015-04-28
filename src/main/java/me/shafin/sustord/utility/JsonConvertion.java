/*
 */
package me.shafin.sustord.utility;

import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author SHAFIN
 */
public class JsonConvertion {

    public static String objectToJsonString(Object object) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(object);
        return jsonString;
    }

    public static <T> String objectListToJsonString(List<T> objectList) {

        String jsonListString = "";
        Gson gson = new Gson();
        int size = objectList.size();

        if (size == 0) {
            jsonListString = "[]";
        }

        for (int i = 0; i < size; i++) {

            String jsonString = gson.toJson(objectList.get(i));

            if (size > 2) {
                if (i == 0) {
                    jsonListString = jsonListString.concat("[" + jsonString + ",");
                } else if (i == size - 1) {
                    jsonListString = jsonListString.concat(jsonString + "]");
                } else {
                    jsonListString = jsonListString.concat(jsonString + ",");
                }
            } else if (size == 2) {
                if (i == 0) {
                    jsonListString = jsonListString.concat("[" + jsonString + ",");
                } else if (i == size - 1) {
                    jsonListString = jsonListString.concat(jsonString + "]");
                }
            } else {
                jsonListString = "[" + jsonString + "]";
            }
        }
        return jsonListString;
    }
}
