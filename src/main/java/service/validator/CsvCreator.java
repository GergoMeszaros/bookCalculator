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

    private List<String[]> dataLines;

    public CsvCreator(ReadConfigFile readConfigFile) {
        this.dataLines = new ArrayList<>();
        this.readConfigFile = readConfigFile;
    }

    private String convertToCsv(String[] data) {
        return String.join(",", data);
    }


    /** This method creates a stringBuilder and escapes the comma if we want to write multiple values into a single field*/
    private String turnWrongFieldsIntoString(List<String> fields) {

        if (fields.size() > 1) {
            StringBuilder result = new StringBuilder();
            result.append('"');
            for (String field : fields) {
                result.append(field);
                if (!field.equals(fields.get(fields.size() - 1)))
                    result.append(",");
            }
            result.append('"');
            return result.toString();
        }

        return fields.get(0);
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

        File outputCsvFile = new File(readConfigFile.getCsvPath());
        dataLines.add(new String[]{"ListingId", "MarketplaceName", "InvalidFields"});

        for (Listing element : dataToCsv) {
            dataLines.add(new String[]{
                    String.valueOf(element.getId()),
                    MarketPlaceType.getMarketPlaceTypeNameFromId(element.getMarketplace()),
                    turnWrongFieldsIntoString(element.getWrongFields())
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
