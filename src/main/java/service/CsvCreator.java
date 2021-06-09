package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvCreator {

    private List<String[]> dataLines = new ArrayList<>();


    private String convertToCsv(String[] data) {
        return String.join(",", data);
    }

    private void deleteCsvIfExists() {
        File csvToDelete = new File("/Users/gergo/codecool/advanced/bookCalculator/src/main/java/file/wrongFields.csv");
        if (csvToDelete.delete()) {
            System.out.println("csv deleted!!!");
        }
    }


    public void convertDataArrayToCsvAndOutputCreated() throws FileNotFoundException {

        deleteCsvIfExists();
        dataLines.add(new String[]{"ListingId", "MarketplaceName", "InvalidField"});

        File outputCsvFile = new File("/Users/gergo/codecool/advanced/bookCalculator/src/main/java/file/wrongFields.csv");

        try (PrintWriter printWriter = new PrintWriter(outputCsvFile)) {
            dataLines.stream()
                    .map(this::convertToCsv)
                    .forEach(printWriter::println);
        }
    }
}
