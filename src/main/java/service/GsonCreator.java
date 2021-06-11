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
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;

public class GsonCreator {

    private static ApiDataValidator apiDataValidator;
    private static HttpDataCollector dataCollector;
    private static String apiResponse;
    private static DatabaseInserter databaseInserter;


    private static void collectDataFromApi(String apiEndPoint) throws IOException {
        dataCollector = HttpDataCollector.getHttpDataCollectorInstance();
        apiResponse = dataCollector.getDataFromApiEndPoint(apiEndPoint);
    }

    public static <T> void  modelListCreator(String apiEndPoint, Class<?> className) throws ClassNotFoundException, IOException, SQLException, ParseException {

        collectDataFromApi(apiEndPoint);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder
                .create();

        T[] listing = gson.fromJson(apiResponse, (Type) className.arrayType());

        if (listing[0] instanceof Listing) {
            apiDataValidator = ApiDataValidator.getApiDataValidatorInstance();
            apiDataValidator.validateApiData((Listing[]) listing);
            callDatabaseInserter(className, listing);
        }

        callDatabaseInserter(className, listing);
    }

    private static <T> void callDatabaseInserter(Class<?> classname, T[] arrayOfModels) throws SQLException, IOException, ParseException {
        databaseInserter = DatabaseInserter.getDatabaseInserterInstance();
        databaseInserter.forwardDataToAppropriateInserter(arrayOfModels, classname);

    }
}
