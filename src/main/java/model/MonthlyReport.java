package model;

import model.MarketPlaceType;

public class MonthlyReport {

    private MarketPlaceType marketPlaceType;
    private String bestListerEmailAddress;
    private Long totalListingPriceOfMonth;
    private Long totalListingCountOfMonth;


    public MonthlyReport(MarketPlaceType marketPlaceType, String bestListerEmailAddress, Long totalListingPriceOfMonth, Long totalListingCountOfMonth) {
        this.marketPlaceType = marketPlaceType;
        this.bestListerEmailAddress = bestListerEmailAddress;
        this.totalListingPriceOfMonth = totalListingPriceOfMonth;
        this.totalListingCountOfMonth = totalListingCountOfMonth;
    }



}
