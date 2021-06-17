package service.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableCreator implements AutoCloseable{

    private final DatabaseConnector databaseConnector;
    private Connection connection;

    public DatabaseTableCreator(DatabaseConnector databaseConnector) {

        this.databaseConnector = databaseConnector;
        createConnection();
    }

    private void createConnection(){
        try {
            connection =databaseConnector.createConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void tableDropper() throws SQLException {

        Statement dropperStatement = connection.createStatement();

        String tableDropper = "" +
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
                        "id varchar(36) NOT NULL," +
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
                        "   id varchar(36) NOT NULL," +
                        "   title varchar(255) NOT NULL," +
                        "   description varchar(255) NOT NULL," +
                        "   location_id varchar(36) NOT NULL," +
                        "   listing_price decimal(65, 2) NOT NULL," +
                        "   currency varchar(3) not null," +
                        "   quantity int NOT NULL," +
                        "   listing_status int," +
                        "   marketplace int NOT NULL," +
                        "   upload_time date," +
                        "   owner_email_address varchar(50) NOT NULL," +
                        "   PRIMARY KEY (id)," +
                        "   CONSTRAINT location_fk " +
                        "       FOREIGN KEY (location_id) REFERENCES location(id), " +
                        "   CONSTRAINT marketplace_fk" +
                        "       FOREIGN KEY (marketplace) REFERENCES marketplace(id)," +
                        "   CONSTRAINT status_fk" +
                        "       FOREIGN KEY (listing_status) REFERENCES listing_status(id))";

        statement.execute(createListing);
        try {
            close();
        } catch (Exception exception) {
            System.out.println("Cannot close db connection");
            exception.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
