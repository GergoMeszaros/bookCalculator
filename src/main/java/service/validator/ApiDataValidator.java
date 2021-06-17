package service.validator;

import model.Listing;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApiDataValidator {

    private final CsvCreator csvCreator;

    public ApiDataValidator(CsvCreator csvCreator) {
        this.csvCreator = csvCreator;
    }

    public void validateApiData(Listing[] listing) throws FileNotFoundException {

        List<Listing> dataToCsv = Arrays.stream(listing)
                .filter(Listing::selfChecker)
                .collect(Collectors.toList());

        csvCreator.convertDataArrayToCsvAndOutputCreated(dataToCsv);

        /*List<Listing> dataToCsv = Arrays.stream(listing)
                .filter(element ->
                        element.getId() == null ||
                                element.getTitle() == null ||
                                element.getDescription() == null ||
                                element.getInventoryItemLocationId() == null ||
                                (element.getListingPrice() <= 0 || !(BigDecimal.valueOf(element.getListingPrice()).scale() > 2)) ||
                                (element.getCurrency() == null || element.getCurrency().length() != 3) ||
                                (element.getQuantity() == null || element.getQuantity() <= 0) ||
                                element.getListingStatus() == null ||
                                element.getMarketplace() == null ||
                                (element.getOwnerEmailAddress() == null || !validateEmailAddress(element.getOwnerEmailAddress())))
                .collect(Collectors.toList());

        csvCreator.convertDataArrayToCsvAndOutputCreated(dataToCsv);*/
    }

}
