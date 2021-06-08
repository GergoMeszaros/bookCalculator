import model.Listing;
import model.ListingStatus;
import model.Location;
import model.Marketplace;
import service.GsonCreator;
import service.HttpDataCollector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Map<String, Class<?>> endPoints = new HashMap<>();
        endPoints.put("https://my.api.mockaroo.com/listing?key=63304c70", Listing.class);
        endPoints.put("https://my.api.mockaroo.com/listingStatus?key=63304c70", ListingStatus.class);
        endPoints.put("https://my.api.mockaroo.com/location?key=63304c70", Location.class);
        endPoints.put("https://my.api.mockaroo.com/marketplace?key=63304c70", Marketplace.class);

        serviceCaller(endPoints);
    }

    public static void serviceCaller(Map<String, Class<?>> endpoints) throws IOException, ClassNotFoundException {

        for (Map.Entry<String, Class<?>> entry : endpoints.entrySet()) {
            GsonCreator.modelListCreator(
                    HttpDataCollector.getDataFromApiEndPoint(entry.getKey()), entry.getValue());
        }
    }
}

