package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import service.database.DatabaseInserter;
import service.validator.ApiDataValidator;
import service.validator.HttpDataCollector;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.text.ParseException;

public class GsonCreator {

    private final ApiDataValidator apiDataValidator;
    private final HttpDataCollector dataCollector;
    private String apiResponse;
    private final DatabaseInserter databaseInserter;

    public GsonCreator(ApiDataValidator apiDataValidator, HttpDataCollector dataCollector, DatabaseInserter databaseInserter) {
        this.apiDataValidator = apiDataValidator;
        this.dataCollector = dataCollector;
        this.databaseInserter = databaseInserter;
    }

    private void collectDataFromApi(String apiEndPoint) throws IOException {
        apiResponse = dataCollector.getDataFromApiEndPoint(apiEndPoint);
    }

    public <T> void modelListCreator(String apiEndPoint, Class<?> className) throws IOException, SQLException, ParseException {

        collectDataFromApi(apiEndPoint);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        T[] listing = gson.fromJson(apiResponse, (Type) className.arrayType());

        if (listing[0] instanceof Listing) {
            apiDataValidator.validateApiData((Listing[]) listing);
            callDatabaseInserter(className, listing);
        }
        callDatabaseInserter(className, listing);
    }

    private <T> void callDatabaseInserter(Class<?> classname, T[] arrayOfModels) throws SQLException, IOException, ParseException {
        databaseInserter.forwardDataToAppropriateInserter(arrayOfModels, classname);
    }
}
