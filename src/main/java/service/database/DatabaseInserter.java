package service.database;

import model.ListingStatus;
import model.Location;
import model.Marketplace;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInserter {

    private DatabaseConnector databaseConnector;
    private static DatabaseInserter databaseInserter;
    private static DatabaseTableCreator databaseTableCreator;

    private DatabaseInserter() {
    }

    public static DatabaseInserter getDatabaseInserterInstance() {
        if (databaseInserter == null) {
            databaseInserter = new DatabaseInserter();
        }
        return databaseInserter;
    }

    private void createTables() throws SQLException, IOException {
        databaseTableCreator = DatabaseTableCreator.getInstance();
        databaseTableCreator.createTable();
    }

    public <T> void forwardDataToAppropriateInserter(T[] arrayOfModels, Class<?> classname) throws SQLException, IOException {
        databaseConnector = DatabaseConnector.getDbConnectorInstance();

        createTables();


        if (classname.equals(Location.class)) {
            insertLocation((Location[]) arrayOfModels);
        } else if (classname.equals(Marketplace.class)) {
            insertMarketPlace(arrayOfModels);
        } else if (classname.equals(ListingStatus.class)) {
            insertListingStatus(arrayOfModels);
        } else {
            insertListing(arrayOfModels);
        }
    }

    private void insertLocation(Location[] locations) throws SQLException, IOException {

        Connection connection = databaseConnector.createConnection();

        String query = "INSERT INTO location VALUES (?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (Location actualElement : locations) {
            preparedStatement.setString(1, String.valueOf(actualElement.getId()));
            preparedStatement.setString(2, actualElement.getManagerName());
            preparedStatement.setString(3, actualElement.getPhone());
            preparedStatement.setString(4, actualElement.getAddressPrimary());
            preparedStatement.setString(5, actualElement.getAddressSecondary());
            preparedStatement.setString(6, actualElement.getCountry());
            preparedStatement.setString(7, actualElement.getTown());
            preparedStatement.setString(8, actualElement.getPostalCode());

            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

    }

    private <T> void insertMarketPlace(T[] marketplaces) {

    }

    private <T> void insertListingStatus(T[] listing_statuses) {

    }

    private <T> void insertListing(T[] listings) {

    }


}
