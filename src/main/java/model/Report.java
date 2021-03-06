package model;


import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Report {

     private String month;
     int year;
     String marketPlaceType;
     String bestListerEmailAddress;
     Float totalListingPrice;
     Long totalListingCount;
     Float averageListingPrice;


    public Report(int year, int month, int marketPlaceType, String bestListerEmailAddress, Float totalListingPriceOfMonth, Long totalListingCountOfMonth, Float averageListingPrice) {
        this.year = year;
        this.month = Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        this.marketPlaceType = setMarketPlaceType(marketPlaceType);
        this.bestListerEmailAddress = bestListerEmailAddress;
        this.totalListingPrice = totalListingPriceOfMonth;
        this.totalListingCount = totalListingCountOfMonth;
        this.averageListingPrice = averageListingPrice;
    }

    public Report() {

    }

    public Report(int year, int marketPlaceType, String bestListerEmailAddress, Float totalListingPrice, Long totalListingCount, Float averageListingPrice ){
        this.totalListingCount = totalListingCount;
        this.year = year;
        this.marketPlaceType = setMarketPlaceType(marketPlaceType);
        this.bestListerEmailAddress = bestListerEmailAddress;
        this.totalListingPrice = totalListingPrice;
        this.averageListingPrice = averageListingPrice;
    }

    String setMarketPlaceType(int marketPlaceId) {
        return MarketPlaceType.getMarketPlaceTypeNameFromId(marketPlaceId);
    }

    @Override
    public String toString() {
        return "MonthlyReport{" +
                "year=" + year +
                ", month=" + month +
                ", marketPlaceType='" + marketPlaceType + '\'' +
                ", bestListerEmailAddress='" + bestListerEmailAddress + '\'' +
                ", totalListingPrice=" + totalListingPrice +
                ", totalListingCount=" + totalListingCount +
                ", averageListingPrice=" + averageListingPrice +
                '}';
    }
}
