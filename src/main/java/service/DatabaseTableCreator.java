package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableCreator {

    private final Connection connection;
    private static DatabaseTableCreator databaseTableCreator;

    private DatabaseTableCreator() throws IOException, SQLException {
        DatabaseConnector databaseConnector = DatabaseConnector.getDbConnectorInstance();
        this.connection = databaseConnector.createConnection();
    }

    public static DatabaseTableCreator getInstance() throws SQLException, IOException {
        if (databaseTableCreator == null) {
            databaseTableCreator = new DatabaseTableCreator();
        }
        return databaseTableCreator;
    }

    private void tableDropper() throws SQLException {

        Statement dropperStatement = connection.createStatement();

        String tableDropper =
                "SET foreign_key_checks = 0;" +
                        "DROP TABLE IF EXISTS listing,listing_status,marketplace, location;" +
                        "SET foreign_key_checks = 1";

        dropperStatement.execute(tableDropper);
    }

    public void createTable() throws SQLException {
        tableDropper();

        Statement statement = connection.createStatement();

        String createListingStatus =
                "CREATE TABLE listing_status (" +
                        "id int NOT NULL," +
                        "status_name varchar(35)," +
                        "PRIMARY KEY (id))";

        statement.execute(createListingStatus);

        String createLocation =
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
                "CREATE TABLE marketplace (" +
                        "id int NOT NULL," +
                        "marketplace_name varchar(255)," +
                        "PRIMARY KEY (id))";

        statement.execute(createMarketplace);

        String createListing =
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
                        "   PRIMARY KEY (id), " +
                        "   CONSTRAINT location_fk " +
                        "       FOREIGN KEY (inventory_item_location_id) REFERENCES location(id), " +
                        "   CONSTRAINT marketplace_fk" +
                        "       FOREIGN KEY (marketplace) REFERENCES marketplace(id)," +
                        "   CONSTRAINT status_fk" +
                        "       FOREIGN KEY (listing_status) REFERENCES listing_status(id))";

        statement.execute(createListing);
    }

}
