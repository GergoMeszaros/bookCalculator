package service.database;

import model.Listing;
import model.ListingStatus;
import model.Location;
import model.Marketplace;

import java.io.IOException;
import java.sql.*;

public class DatabaseInserter {

    private final DatabaseConnector databaseConnector;
    private final DatabaseTableCreator databaseTableCreator;
    private Connection connection;


    public DatabaseInserter(DatabaseConnector databaseConnector, DatabaseTableCreator databaseTableCreator) {
        this.databaseConnector = databaseConnector;
        this.databaseTableCreator = databaseTableCreator;
        createConnection();

        try {
            createTables();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private void createConnection() {
        try {
            this.connection = databaseConnector.createConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void createTables() throws SQLException, IOException {
        databaseTableCreator.createTable();
    }

    public <T> void forwardDataToAppropriateInserter(T[] arrayOfModels, Class<?> classname) throws SQLException {

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

    private void insertLocation(Location[] locations) throws SQLException {

        createConnection();
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

    private void insertMarketPlace(Marketplace[] marketplaces) throws SQLException {

        createConnection();
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

    private void insertListingStatus(ListingStatus[] listing_statuses) throws SQLException {

        createConnection();
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

    private void insertListing(Listing[] listings) throws SQLException {

        createConnection();
        connection.setAutoCommit(false);

        String query = "" +
                "INSERT INTO listing VALUES (?,?,?,?,?,?,?,?,?,STR_TO_DATE(?, '%m/%d/%Y'),?) " +
                "ON DUPLICATE KEY UPDATE id = id;" +
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
