package service;

import model.Listing;
import model.MarketPlaceType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvCreator {

    private List<String[]> dataLines;

    public CsvCreator() {
        this.dataLines = new ArrayList<>();
    }

    private String convertToCsv(String[] data) {
        return String.join(",", data);
    }

    private String turnWrongFieldsIntoString(String[] fields){
        return String.join("-", fields);

    }

    private void deleteCsvIfExists() {
        File csvToDelete = new File("/Users/gergo/codecool/advanced/bookCalculator/src/main/java/file/wrongFields.csv");
        if (csvToDelete.delete()) {
            System.out.println("csv deleted!!!");
        }
    }

    public void convertDataArrayToCsvAndOutputCreated(List<Listing> dataToCsv) throws FileNotFoundException {

        deleteCsvIfExists();
        File outputCsvFile = new File("/Users/gergo/codecool/advanced/bookCalculator/src/main/java/file/wrongFields.csv");

        dataLines.add(new String[]{"ListingId", "MarketplaceName", "InvalidFields"});

        for (Listing element : dataToCsv) {
            dataLines.add(new String[]{String.valueOf(element.getId()),
                    MarketPlaceType.getMarketPlaceTypeNameFromId(element.getMarketplace()),
                    "----"
            });
        }

        try (PrintWriter printWriter = new PrintWriter(outputCsvFile)) {
            dataLines.stream()
                    .map(this::convertToCsv)
                    .forEach(printWriter::println);
        }
    }
}
