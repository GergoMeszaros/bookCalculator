package service.database;

import javax.xml.crypto.Data;
import java.sql.Connection;

public class DatabaseInserter {

    private Connection connection;
    private static DatabaseInserter databaseInserter;

    private DatabaseInserter(){};

    public DatabaseInserter getDatabaseInserterInstance(){
        if(databaseInserter == null){
            databaseInserter = new DatabaseInserter();
        }
        return databaseInserter;
    }



}
