package service.validator;

import model.Listing;
import service.CsvCreator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApiDataValidator {

    private static ApiDataValidator apiDataValidator;
    private final CsvCreator csvCreator = new CsvCreator();

    private ApiDataValidator() {
    }

    public static ApiDataValidator getApiDataValidatorInstance() {
        if (apiDataValidator == null) {
            apiDataValidator = new ApiDataValidator();
        }
        return apiDataValidator;
    }

    public void validateApiData(Listing[] listing) throws FileNotFoundException {

        List<Listing> dataToCsv = Arrays.stream(listing)
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

        csvCreator.convertDataArrayToCsvAndOutputCreated(dataToCsv);
    }

    private boolean validateEmailAddress(String emailAddress) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }


}
