package service.report;

import model.MarketPlaceType;

public class AnnualReport extends MonthlyReport{

    private Long totalListingCount;


    public AnnualReport(MarketPlaceType marketPlaceType, String bestListerEmailAddress, Long totalListingPriceOfMonth, Long totalListingCountOfMonth, Long totalListingCount) {
        super(marketPlaceType, bestListerEmailAddress, totalListingPriceOfMonth, totalListingCountOfMonth);
        this.totalListingCount = totalListingCount;
    }
}
