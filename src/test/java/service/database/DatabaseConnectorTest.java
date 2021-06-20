package service.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.config.ReadConfigFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {

    MysqlDataSource dataSource;
    ReadConfigFile readConfigFile;
    DatabaseConnector databaseConnector;

    @BeforeEach
    void setup() throws IOException {

        dataSource = new MysqlDataSource();
        readConfigFile = new ReadConfigFile(new Properties());
        databaseConnector = new DatabaseConnector(readConfigFile, dataSource);
    }

    @Test
    void checkWrongDatabaseConnection() {

        dataSource.setURL(" ");
        dataSource.setUser(" ");
        dataSource.setPassword(" ");

        assertThrows(SQLException.class, () ->
                dataSource.getConnection());
    }


    @Test
    void checkGoodConnection() throws SQLException {
        Connection connection = databaseConnector.createConnection();
        assertTrue(connection.isValid(0));
    }
}