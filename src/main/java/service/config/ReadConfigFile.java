package service.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {
    InputStream inputStream;
    String dbUsername;
    String dbPassword;

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
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

        } catch (IOException exception) {
            System.out.println("Exception " + exception);
        } finally {
            inputStream.close();
        }


    }


}
