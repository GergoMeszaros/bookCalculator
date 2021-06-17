package service.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import service.config.ReadConfigFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private final ReadConfigFile readConfigFile;

    public DatabaseConnector(ReadConfigFile readConfigFile) {
        this.readConfigFile = readConfigFile;
    }

    public Connection createConnection() throws SQLException {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(readConfigFile.getDbUrl());
        dataSource.setUser(readConfigFile.getDbUsername());
        dataSource.setPassword(readConfigFile.getDbPassword());
        return dataSource.getConnection();
    }
}
