package service.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {

    MysqlDataSource dataSource;
    DatabaseConnector databaseConnector;
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
}