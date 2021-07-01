package model;

public enum MarketPlaceType {

    EBAY(1, "EBAY"),
    AMAZON(2, "AMAZON");

    private final int id;
    private final String name;

    MarketPlaceType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public static String getMarketPlaceTypeNameFromId(int marketplaceId) {
        for (MarketPlaceType type : MarketPlaceType.values()) {
            if (type.getId() == marketplaceId) {
                return type.name;
            }
        }
        return null;
    }
}


