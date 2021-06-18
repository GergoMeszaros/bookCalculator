package service.config;

import model.Listing;
import model.ListingStatus;
import model.Location;
import model.Marketplace;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Endpoint {

    private Map<String, Class<?>> endpoints;
    private final ReadConfigFile readConfigFile;

    public Endpoint(ReadConfigFile readConfigFile) {
        this.readConfigFile = readConfigFile;
        createMap();
    }

    private void createMap() {
        endpoints = new TreeMap<>(Collections.reverseOrder());

        endpoints.put(readConfigFile.getListing(), Listing.class);
        endpoints.put(readConfigFile.getListingStatus(), ListingStatus.class);
        endpoints.put(readConfigFile.getMarketplace(), Marketplace.class);
        endpoints.put(readConfigFile.getLocation(), Location.class);
    }

    public Map<String, Class<?>> getEndpoints() {
        return endpoints;
    }
}
