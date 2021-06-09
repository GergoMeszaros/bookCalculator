import model.*;
import service.DatabaseConnector;
import service.GsonCreator;
import service.HttpDataCollector;
import service.config.ReadConfigFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Map<String, Class<?>> endPoints = new HashMap<>();
       // endPoints.put("https://my.api.mockaroo.com/listing?key=63304c70", Listing.class);
       // endPoints.put("https://my.api.mockaroo.com/listingStatus?key=63304c70", ListingStatus.class);
        //endPoints.put("https://my.api.mockaroo.com/location?key=63304c70", Location.class);
        //endPoints.put("https://my.api.mockaroo.com/marketplace?key=63304c70", Marketplace.class);

        //serviceCaller(endPoints);
    }

    public static void serviceCaller(Map<String, Class<?>> endpoints) throws IOException, ClassNotFoundException {

        for (Map.Entry<String, Class<?>> entry : endpoints.entrySet()) {
            System.out.println(Arrays.toString(GsonCreator.modelListCreator(
                    HttpDataCollector.getDataFromApiEndPoint(entry.getKey()), entry.getValue())));
        }
    }
}

