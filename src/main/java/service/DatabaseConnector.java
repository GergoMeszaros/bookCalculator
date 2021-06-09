package service;


import com.mysql.cj.jdbc.MysqlDataSource;
import service.config.ReadConfigFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private static DatabaseConnector databaseConnector;
    private final ReadConfigFile readConfigFile = new ReadConfigFile();

    private DatabaseConnector() throws IOException {
    }

    public static DatabaseConnector getDbConnectorInstance() throws IOException {
        if (databaseConnector == null) {
            databaseConnector = new DatabaseConnector();
        }
        return databaseConnector;
    }

    public Connection createConnection() throws SQLException, IOException {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(readConfigFile.getDbUrl());
        dataSource.setUser(readConfigFile.getDbUsername());
        dataSource.setPassword(readConfigFile.getDbPassword());
        return dataSource.getConnection();
    }
}
