package service.validator;

import model.Listing;
import model.MarketPlaceType;
import service.config.ReadConfigFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvCreator {

    private final ReadConfigFile readConfigFile;

    public CsvCreator(ReadConfigFile readConfigFile) {
        this.readConfigFile = readConfigFile;
    }

    private String convertToCsv(String[] data) {
        return String.join(",", data);
    }


    /**
     * This method creates a stringBuilder and escapes the comma if we want to write multiple values into a single field
     */
    private String turnInvalidFieldNamesIntoString(List<String> fields) {

        int fieldsLength = fields.size();

        if (fieldsLength > 1) {
            return escapeCommasBetweenStringsInCsv(fields, fieldsLength);
        }
        return fields.get(0);
    }

    private String escapeCommasBetweenStringsInCsv(List<String> fieldsToSeparate, int fieldsLength) {

        StringBuilder fieldNamesToCommaSeparatedString = new StringBuilder();
        fieldNamesToCommaSeparatedString.append('"');

        for (String fieldName : fieldsToSeparate) {
            fieldNamesToCommaSeparatedString.append(fieldName);
            if (!fieldName.equals(fieldsToSeparate.get(fieldsLength - 1)))
                fieldNamesToCommaSeparatedString.append(",");
        }
        fieldNamesToCommaSeparatedString.append('"');

        return fieldNamesToCommaSeparatedString.toString();
    }

    private void deleteCsvIfExists() {
        String csvPath = readConfigFile.getCsvPath();
        File csvToDelete = new File(csvPath);
        if (csvToDelete.delete()) {
            System.out.println("Deleted existing InvalidFields.csv file");
        }
    }

    public void convertDataArrayToCsvAndOutputCreated(List<Listing> dataToCsv) {

        deleteCsvIfExists();

        System.out.println("Creating InvalidFields.csv file");

        List<String[]> dataLines = new ArrayList<>();

        File outputCsvFile = new File(readConfigFile.getCsvPath());
        dataLines.add(readConfigFile.getCsvHeader());

        for (Listing element : dataToCsv) {
            dataLines.add(new String[]{
                    String.valueOf(element.getId()),
                    MarketPlaceType.getMarketPlaceTypeNameFromId(element.getMarketplace()),
                    turnInvalidFieldNamesIntoString(element.getWrongFields())
            });
        }

        try (PrintWriter printWriter = new PrintWriter(outputCsvFile)) {
            dataLines.stream()
                    .map(this::convertToCsv)
                    .forEach(printWriter::println);

        } catch (FileNotFoundException exception) {
            System.out.println("Csv file not found");
            exception.printStackTrace();
        }
    }
}
