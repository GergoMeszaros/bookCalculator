package service.validator;

import model.Listing;
import model.ListingStatusType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApiDataValidator {

    private final CsvCreator csvCreator;

    public ApiDataValidator(CsvCreator csvCreator) {
        this.csvCreator = csvCreator;
    }

    public Listing[] validateApiData(Listing[] listing) {

        List<Listing> dataToCsv = Arrays.stream(listing)
                .filter(Listing::selfChecker)
                .collect(Collectors.toList());

        csvCreator.convertDataArrayToCsvAndOutputCreated(dataToCsv);

        return removeListingsInWhichStatusDoesNotMatch(listing);
    }

    private Listing[] removeListingsInWhichStatusDoesNotMatch(Listing[] listings) {

        List<Integer> validListingStatusIds = ListingStatusType.getIdList();

        List<Listing> listingArray = Arrays.stream(listings)
                .filter(element -> validListingStatusIds.contains(element.getListingStatus()))
                .collect(Collectors.toList());

        Listing[] result = new Listing[listingArray.size()];
        listingArray.toArray(result);

        return result;
    }
}
