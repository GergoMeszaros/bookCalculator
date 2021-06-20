package service.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.config.ReadConfigFile;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DatabaseConnectorTest {

    private MysqlDataSource dataSource;
    private DatabaseConnector databaseConnector;
    Connection connection;


    @BeforeEach
    void setup() {
        dataSource = Mockito.mock(MysqlDataSource.class);
        databaseConnector = Mockito.mock(DatabaseConnector.class);
    }

    @Test
    void checkWrongDatabaseConnection() {

        dataSource.setURL(" ");
        dataSource.setUser(" ");
        dataSource.setPassword(" ");

        try {
            connection = dataSource.getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        assertNull(connection);
    }

    @Test
    void returnConnection() throws SQLException {
        when(databaseConnector.createConnection()).thenReturn(connection);
    }
}