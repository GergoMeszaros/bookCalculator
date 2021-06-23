import com.mysql.cj.jdbc.MysqlDataSource;
import service.GsonCreator;
import service.config.Endpoint;
import service.config.ReadConfigFile;
import service.database.DatabaseConnector;
import service.database.DatabaseInserter;
import service.database.DatabaseTableCreator;
import service.report.FtpUploader;
import service.report.ReportCreator;
import service.report.ReportToJsonWriter;
import service.validator.ApiDataValidator;
import service.validator.CsvCreator;
import service.validator.HttpDataCollector;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.Properties;

public class ServiceCaller {

    public ServiceCaller() {
    }


    public void startApplication() {
        try {
            createInstances();
        } catch (IOException | SQLException | ParseException | InterruptedException exception) {
            exception.printStackTrace();
            System.out.println("Error while starting application " + exception);
        }
    }
    private void createInstances() throws IOException, SQLException, ParseException, InterruptedException {
        Properties properties = new Properties();
        MysqlDataSource mysqlDataSource = new MysqlDataSource();

        ReadConfigFile readConfigFile = new ReadConfigFile(properties);
        HttpDataCollector httpDataCollector = new HttpDataCollector();
        FtpUploader ftpUploader = new FtpUploader(readConfigFile);
        Endpoint endpoint = new Endpoint(readConfigFile);

        CsvCreator csvCreator = new CsvCreator(readConfigFile);
        ApiDataValidator apiDataValidator = new ApiDataValidator(csvCreator);

        DatabaseConnector databaseConnector = new DatabaseConnector(readConfigFile, mysqlDataSource);
        DatabaseTableCreator databaseTableCreator = new DatabaseTableCreator(databaseConnector);
        DatabaseInserter databaseInserter = new DatabaseInserter(databaseConnector, databaseTableCreator);

        GsonCreator gsonCreator = new GsonCreator(apiDataValidator, httpDataCollector, databaseInserter);

        ReportToJsonWriter reportToJsonWriter = new ReportToJsonWriter(readConfigFile);
        ReportCreator reportCreator = new ReportCreator(databaseConnector, reportToJsonWriter);

        execute(endpoint, gsonCreator, reportCreator, ftpUploader);
    }

    private void execute(Endpoint endpoint, GsonCreator gsonCreator, ReportCreator reportCreator, FtpUploader ftpUploader) throws SQLException, IOException {

        for (Map.Entry<String, Class<?>> entry : endpoint.getEndpoints().entrySet()) {
            gsonCreator.modelListCreator(entry.getKey(), entry.getValue());
        }

        reportCreator.startReporting();
        ftpUploader.upload();

    }

}
