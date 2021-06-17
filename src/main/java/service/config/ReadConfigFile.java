package service.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {

    InputStream inputStream;
    String dbUsername;
    String dbPassword;
    String dbUrl;
    String reportFileName;
    String csvName;
    String ftpUsername;
    String ftpPassword;
    String ftpServer;
    String listing;
    String listingStatus;
    String marketplace;
    String location;

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public String getCsvName() {
        return csvName;
    }

    public String getFtpUsername() {
        return ftpUsername;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public String getFtpServer() {
        return ftpServer;
    }

    public String getListing() {
        return listing;
    }

    public String getListingStatus() {
        return listingStatus;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public String getLocation() {
        return location;
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
            dbUrl = properties.getProperty("dbUrl");
            reportFileName = properties.getProperty("fileName");
            csvName = properties.getProperty("csvName");
            ftpServer = properties.getProperty("ftpServer");
            ftpUsername = properties.getProperty("ftpUsername");
            ftpPassword = properties.getProperty("ftpPassword");
            listing = properties.getProperty("listing");
            listingStatus = properties.getProperty("listingStatus");
            marketplace = properties.getProperty("marketplace");
            location = properties.getProperty("location");

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

}
