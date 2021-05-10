package com.poc.CustomerOperations.common;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class CommonUtils {

    public static <T> T transformObject(Object from, Class<T> valueType) throws Exception {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(from);
        JsonElement json = gson.fromJson(jsonStr, JsonElement.class);
        return gson.fromJson(gson.toJson(json), valueType);
    }

}
