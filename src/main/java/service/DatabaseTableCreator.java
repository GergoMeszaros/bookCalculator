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

        String createListing = "" +
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




    }

}
