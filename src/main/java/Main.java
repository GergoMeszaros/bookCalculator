import service.GsonCreator;
import service.HttpDataCollector;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Map<String, String> endPoints = new HashMap<>();
        endPoints.put("https://my.api.mockaroo.com/listing?key=63304c70", "Listing");
        endPoints.put("https://my.api.mockaroo.com/listingStatus?key=63304c70", "ListingStatus");
        endPoints.put("https://my.api.mockaroo.com/location?key=63304c70", "Location");
        endPoints.put("https://my.api.mockaroo.com/marketplace?key=63304c70", "Marketplace");

        serviceCaller(endPoints);

    }

    public static void serviceCaller(Map<String, String> endpoints) throws IOException, ClassNotFoundException {

        for (Map.Entry<String, String> entry : endpoints.entrySet()) {
            GsonCreator.modelListCreator(
                    HttpDataCollector.getDataFromApiEndPoint(entry.getKey()), entry.getValue()
            );
        }
    }

}
