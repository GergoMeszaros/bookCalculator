package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableCreator {

    private final DatabaseConnector databaseConnector;

    public DatabaseTableCreator() throws IOException {
        this.databaseConnector = DatabaseConnector.getDbConnectorInstance();
    }

    private void createTable() throws SQLException, IOException {

        Connection connection = databaseConnector.createConnection();
        Statement statement = connection.createStatement();

        String createListing =
                "DROP TABLE IF EXISTS listing; " +
                        "CREATE TABLE listing (" +
                        "   id binary(16) NOT NULL," +
                        "   title varchar(35) NOT NULL," +
                        "   description varchar(255) NOT NULL," +
                        "   inventory_item_location_id binary(16) NOT NULL," +
                        "   listing_price decimal(65, 2) NOT NULL," +
                        "   currency varchar(3) not null," +
                        "   quantity int NOT NULL," +
                        "   listing_status int NOT NULL," +
                        "   marketplace int NOT NULL," +
                        "   upload_time date NOT NULL," +
                        "   owner_email_address varchar(50) NOT NULL," +
                        "   PRIMARY KEY (id)," +
                        "   FOREIGN KEY (inventory_item_location_id) REFERENCES location(id)," +
                        "   FOREIGN KEY (marketplace) REFERENCES marketplace(id)," +
                        "   FOREIGN KEY (listing_status) REFERENCES listing_status(id))";

        statement.execute(createListing);

        String createListingStatus =
                "DROP TABLE IF EXISTS listing_status;" +
                        "CREATE TABLE listing_status (" +
                        "id int NOT NULL," +
                        "status_name varchar(35)," +
                        "PRIMARY KEY (id))";

        statement.execute(createListingStatus);

        String createLocation =
                "DROP TABLE IF EXISTS location;" +
                        "CREATE TABLE location (" +
                        "id binary(16) NOT NULL," +
                        "manager_name varchar(255) NOT NULL," +
                        "phone varchar(15) NOT NULL," +
                        "address_primary varchar(255)," +
                        "address_secondary varchar(255)," +
                        "country varchar(35)," +
                        "town varchar(35)," +
                        "postal_code varchar(35)," +
                        "PRIMARY KEY (id))";

        statement.execute(createLocation);

        String createMarketplace =
                "DROP TABLE IF EXISTS marketplace;" +
                        "CREATE TABLE marketplace (" +
                        "id int NOT NULL," +
                        "marketplace_name varchar(255)," +
                        "PRIMARY KEY (id))";

        statement.execute(createMarketplace);
    }

}
