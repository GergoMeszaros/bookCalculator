package service.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.config.ReadConfigFile;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectorTest {

    private ReadConfigFile readConfigFile;
    private MysqlDataSource dataSource;


    @BeforeEach
    void setup(){
        readConfigFile = Mockito.mock(ReadConfigFile.class);
        dataSource = Mockito.mock(MysqlDataSource.class);
    }

    @Test
    void checkDatabaseConnection() throws SQLException {

        dataSource.setURL(readConfigFile.getDbUrl());
        dataSource.setUser(readConfigFile.getDbUsername());
        dataSource.setPassword(readConfigFile.getDbPassword());

        assertTrue(dataSource.getConnection().isValid(0));

    }

    @Test
    void throwExceptionIfConnectionParametersAreWrong(){

        Assertions.assertThrows(SQLException.class, () ->{
            dataSource.setURL(" ");
            dataSource.setUser(" ");
            dataSource.setPassword(" ");
        });

    }
}