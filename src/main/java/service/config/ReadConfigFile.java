package service.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {

    private InputStream inputStream;
    private final Properties properties;

    private String dbUsername;
    private String dbPassword;
    private String dbUrl;
    private String reportFileName;
    private String reportFilePath;
    private String csvPath;
    private String ftpUsername;
    private String ftpPassword;
    private String ftpServer;
    private String listing;
    private String listingStatus;
    private String marketplace;
    private String location;
    private String [] csvHeader;

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

    public String getReportFilePath() {
        return reportFilePath;
    }

    public String getCsvPath() {
        return csvPath;
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

    public String[] getCsvHeader() {
        return csvHeader;
    }

    public ReadConfigFile(Properties properties) throws IOException {
        this.properties = properties;
        getPropertyValues();
    }

    public void getPropertyValues() throws IOException {

        try {
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
            reportFilePath = properties.getProperty("reportFilePath");
            reportFileName = properties.getProperty("reportFileName");
            csvPath = properties.getProperty("csvPath");
            ftpServer = properties.getProperty("ftpServer");
            ftpUsername = properties.getProperty("ftpUsername");
            ftpPassword = properties.getProperty("ftpPassword");
            listing = properties.getProperty("listing");
            listingStatus = properties.getProperty("listingStatus");
            marketplace = properties.getProperty("marketplace");
            location = properties.getProperty("location");
            csvHeader = new String[]{properties.getProperty("csvHeader")};

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

}
