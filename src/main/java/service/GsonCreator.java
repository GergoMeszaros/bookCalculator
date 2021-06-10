package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import service.validator.ApiDataValidator;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;

public class GsonCreator {

    private static ApiDataValidator apiDataValidator;


    public static <T> T[] modelListCreator(String input, Class<?> className) throws ClassNotFoundException, FileNotFoundException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .setDateFormat("MM/dd/yyyy")
                .create();


      /*  Type userListType = new TypeToken<ArrayList<?>>() {}.getType();
        ArrayList<?> userArray = gson.fromJson(input, userListType);

        System.out.println(userArray.get(0));
        */
        T[] listing = gson.fromJson(input, (Type) className.arrayType());

        if (listing[0] instanceof Listing) {
            apiDataValidator = ApiDataValidator.getApiDataValidatorInstance();
            apiDataValidator.validateApiData((Listing[]) listing);
        }
        for (T t : listing) {
            System.out.println(t);
        }

        return listing;
    }
}
