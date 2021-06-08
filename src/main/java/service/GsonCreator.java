package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GsonCreator {


    public static <T> T[] modelListCreator(String input, Class<?> className) throws ClassNotFoundException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .setDateFormat("MM/dd/yyyy")
                .create();


      /*  Type userListType = new TypeToken<ArrayList<?>>() {}.getType();
        ArrayList<?> userArray = gson.fromJson(input, userListType);

        System.out.println(userArray.get(0));
        */
        return gson.fromJson(input, (Type) className.arrayType());
    }
}
