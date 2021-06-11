import model.*;
import service.*;
import service.config.ReadConfigFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, ParseException {

        Map<String, Class<?>> endPoints = new TreeMap<>(Collections.reverseOrder());
        endPoints.put("https://my.api.mockaroo.com/listingStatus?key=63304c70", ListingStatus.class);
        endPoints.put("https://my.api.mockaroo.com/location?key=63304c70", Location.class);
        endPoints.put("https://my.api.mockaroo.com/marketplace?key=63304c70", Marketplace.class);
        endPoints.put("https://my.api.mockaroo.com/listing?key=63304c70", Listing.class);

        serviceCaller(endPoints);
    }

    public static void serviceCaller(Map<String, Class<?>> endpoints) throws IOException, ClassNotFoundException, SQLException, ParseException {

        for (Map.Entry<String, Class<?>> entry : endpoints.entrySet()) {
            GsonCreator.modelListCreator(entry.getKey(), entry.getValue());
        }
    }
}

