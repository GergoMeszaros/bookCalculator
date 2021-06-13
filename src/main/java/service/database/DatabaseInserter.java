package service.database;

import model.Listing;
import model.ListingStatus;
import model.Location;
import model.Marketplace;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatabaseInserter {

    private DatabaseConnector databaseConnector;
    private static DatabaseInserter databaseInserter;

    private DatabaseInserter() {
    }

    public static DatabaseInserter getDatabaseInserterInstance() throws SQLException, IOException {
        if (databaseInserter == null) {
            databaseInserter = new DatabaseInserter();
            createTables();
        }
        return databaseInserter;
    }

    private static void createTables() throws SQLException, IOException {
        DatabaseTableCreator databaseTableCreator = DatabaseTableCreator.getInstance();
        databaseTableCreator.createTable();
    }

    public <T> void forwardDataToAppropriateInserter(T[] arrayOfModels, Class<?> classname) throws SQLException, IOException, ParseException {
        databaseConnector = DatabaseConnector.getDbConnectorInstance();

        if (classname.equals(Location.class)) {
            insertLocation((Location[]) arrayOfModels);
        } else if (classname.equals(Marketplace.class)) {
            insertMarketPlace((Marketplace[]) arrayOfModels);
        } else if (classname.equals(ListingStatus.class)) {
            insertListingStatus((ListingStatus[]) arrayOfModels);
        } else {
            insertListing((Listing[]) arrayOfModels);
        }
    }

    private void insertLocation(Location[] locations) throws SQLException, IOException {

        Connection connection = databaseConnector.createConnection();
        connection.setAutoCommit(false);

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
        connection.commit();
        connection.close();
    }

    private void insertMarketPlace(Marketplace[] marketplaces) throws SQLException, IOException {

        Connection connection = databaseConnector.createConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO marketplace VALUES (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (Marketplace actualElement : marketplaces) {
            preparedStatement.setInt(1, actualElement.getId());
            preparedStatement.setString(2, actualElement.getMarketplaceName());

            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        connection.commit();
        connection.close();
    }

    private void insertListingStatus(ListingStatus[] listing_statuses) throws SQLException, IOException {
        Connection connection = databaseConnector.createConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO listing_status VALUES (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (ListingStatus actualElement : listing_statuses) {
            preparedStatement.setInt(1, actualElement.getId());
            preparedStatement.setString(2, actualElement.getStatusName());

            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
        connection.close();
    }

    private void insertListing(Listing[] listings) throws SQLException, IOException, ParseException {
        Connection connection = databaseConnector.createConnection();
        connection.setAutoCommit(false);

        String query = "" +
                "INSERT INTO listing VALUES (?,?,?,?,?,?,?,?,?,STR_TO_DATE(?, '%m/%d/%Y'),?);" +
                "SET FOREIGN_KEY_CHECKS = 0";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (Listing actualElement : listings) {

            preparedStatement.setString(1, String.valueOf(actualElement.getId()));
            preparedStatement.setString(2, actualElement.getTitle());
            preparedStatement.setString(3, actualElement.getDescription());
            preparedStatement.setString(4, String.valueOf(actualElement.getInventoryItemLocationId()));
            preparedStatement.setFloat(5, actualElement.getListingPrice());
            preparedStatement.setString(6, actualElement.getCurrency());
            preparedStatement.setInt(7, actualElement.getQuantity());
            preparedStatement.setInt(8, actualElement.getListingStatus());
            preparedStatement.setInt(9, actualElement.getMarketplace());
            preparedStatement.setString(10, actualElement.getUploadTime());
            preparedStatement.setString(11, actualElement.getOwnerEmailAddress());

            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
        connection.close();
    }
}
