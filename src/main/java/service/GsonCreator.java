package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import service.database.DatabaseConnector;
import service.database.DatabaseInserter;
import service.validator.ApiDataValidator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Arrays;

public class GsonCreator {

    private static ApiDataValidator apiDataValidator;
    private static HttpDataCollector dataCollector;
    private static String apiResponse;
    private static DatabaseInserter databaseInserter;


    private static void collectDataFromApi(String apiEndPoint) throws IOException {
        dataCollector = HttpDataCollector.getHttpDataCollectorInstance();
        apiResponse = dataCollector.getDataFromApiEndPoint(apiEndPoint);
    }

    public static <T> T[] modelListCreator(String apiEndPoint, Class<?> className) throws ClassNotFoundException, IOException, SQLException {

        collectDataFromApi(apiEndPoint);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .setDateFormat("MM/dd/yyyy")
                .create();

      /*  Type userListType = new TypeToken<ArrayList<?>>() {}.getType();
        ArrayList<?> userArray = gson.fromJson(input, userListType);

        System.out.println(userArray.get(0));
        */
        T[] listing = gson.fromJson(apiResponse, (Type) className.arrayType());

        if (listing[0] instanceof Listing) {
            apiDataValidator = ApiDataValidator.getApiDataValidatorInstance();
            apiDataValidator.validateApiData((Listing[]) listing);
        }

        callDatabaseInserter(className, listing);
        return listing;
    }

    private static <T> void callDatabaseInserter(Class<?> classname, T[] arrayOfModels) throws SQLException, IOException {
        databaseInserter = DatabaseInserter.getDatabaseInserterInstance();
        databaseInserter.forwardDataToAppropriateInserter(arrayOfModels, classname);

    }
}
