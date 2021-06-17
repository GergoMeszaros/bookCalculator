package service.report;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import service.config.ReadConfigFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpUploader {

    private final String ftpAddress;
    private final String ftpUsername;
    private final String ftpPassword;
    private final String reportFilePath;
    private final String reportFileName;


    public FtpUploader(ReadConfigFile readConfigFile) {
        ftpAddress = readConfigFile.getFtpServer();
        ftpUsername = readConfigFile.getFtpUsername();
        ftpPassword = readConfigFile.getFtpPassword();
        reportFileName = readConfigFile.getReportFileName();
        reportFilePath = readConfigFile.getReportFilePath();

    }

    public void upload() {

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(ftpAddress);
            ftpClient.login(ftpUsername, ftpPassword);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File file = new File(reportFilePath);

            InputStream inputStream = new FileInputStream(file);
            System.out.println("Uploading file");
            boolean done = ftpClient.storeFile(reportFileName, inputStream);
            inputStream.close();

            if (done) {
                System.out.println("File successfully uploaded");
            } else {
                System.out.println("Failed to upload file");
            }

        } catch (IOException exception) {
            System.out.println("Failed to connect ftp " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
