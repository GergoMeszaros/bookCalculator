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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    private void collectDataFromApi(String apiEndPoint) {
        apiResponse = dataCollector.getDataFromApiEndPoint(apiEndPoint);
    }

    public <T> void modelListCreator(String apiEndPoint, Class<?> className) throws IOException, SQLException {

        collectDataFromApi(apiEndPoint);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        T[] databaseModel = gson.fromJson(apiResponse, (Type) className.arrayType());
        Listing[] listings = new Listing[0];

        if (databaseModel[0] instanceof Listing) {
            listings = apiDataValidator.validateApiData((Listing[]) databaseModel);
        }

        if (listings.length > 0) {
            callDatabaseInserter(className, listings);
        } else {
            callDatabaseInserter(className, databaseModel);
        }

    }

    private <T> void callDatabaseInserter(Class<?> classname, T[] arrayOfModels) throws SQLException {
        databaseInserter.forwardDataToAppropriateInserter(arrayOfModels, classname);
    }

}



