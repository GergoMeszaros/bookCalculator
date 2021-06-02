package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonCreator {


    public static <Type> Type[] modelListCreator(StringBuffer input, String className) throws ClassNotFoundException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .setDateFormat("MM/dd/yyyy")
                .create();

        return gson.fromJson(String.valueOf(input),
                (java.lang.reflect.Type) classPicker(className).arrayType());
    }

    private static Class<?> classPicker(String className) throws ClassNotFoundException {
        return Class.forName("model." + className);
    }
}
