package service.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import service.config.ReadConfigFile;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private final ReadConfigFile readConfigFile;
    private final MysqlDataSource dataSource;

    public DatabaseConnector(ReadConfigFile readConfigFile, MysqlDataSource dataSource) {
        this.readConfigFile = readConfigFile;
        this.dataSource = dataSource;
    }

    public Connection createConnection() throws SQLException {

        dataSource.setURL(readConfigFile.getDbUrl());
        dataSource.setUser(readConfigFile.getDbUsername());
        dataSource.setPassword(readConfigFile.getDbPassword());
        return dataSource.getConnection();
    }
}
