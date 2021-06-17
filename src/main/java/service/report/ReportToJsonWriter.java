package service.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import service.config.ReadConfigFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportToJsonWriter {

    private final ReadConfigFile readConfigFile;
    private FileWriter fileWriter;
    private String reportFilePath;

    public ReportToJsonWriter(ReadConfigFile readConfigFile) {
        this.readConfigFile = readConfigFile;
        removeJsonFileIfExists();
    }

    public <T> void createJsonFromArrayList(List<T> report) {

        try {
            fileWriter = new FileWriter(reportFilePath, true);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonStringFromReports = gson.toJson(report);

        try {
            fileWriter.append(jsonStringFromReports);
            fileWriter.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void removeJsonFileIfExists() {

        reportFilePath = readConfigFile.getReportFilePath();

        File file = new File(reportFilePath);
        if (file.delete())
            System.out.println("Deleted existing Report.json file");
    }
}
