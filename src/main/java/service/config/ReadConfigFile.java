package service.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {
    InputStream inputStream;
    String dbUsername;
    String dbPassword;
    String dbName;
    String dbUrl;
    String driverName;

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDriverName() {
        return driverName;
    }

    public ReadConfigFile() throws IOException {
        getPropertyValues();
    }

    public void getPropertyValues() throws IOException {

        try {
            Properties properties = new Properties();
            String configFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Config file " + configFileName + " not found in the classpath!!!");
            }
            dbUsername = properties.getProperty("dbUsername");
            dbPassword = properties.getProperty("dbPassword");
            dbName = properties.getProperty("dbName");
            dbUrl = properties.getProperty("dbUrl");
            driverName = properties.getProperty("driverName");

        } catch (IOException exception) {
            System.out.println("Exception " + exception);
        } finally {
            inputStream.close();
        }


    }


}
