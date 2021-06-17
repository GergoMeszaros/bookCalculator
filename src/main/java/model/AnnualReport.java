package model;

public class AnnualReport extends MonthlyReport {


    public AnnualReport(int year, int marketPlaceType, String bestListerEmailAddress, Float totalListingPrice, Long totalListingCount, Float averageListingPrice ){
        this.totalListingCount = totalListingCount;
        this.year = year;
        this.marketPlaceType = setMarketPlaceType(marketPlaceType);
        this.bestListerEmailAddress = bestListerEmailAddress;
        this.totalListingPrice = totalListingPrice;
        this.averageListingPrice = averageListingPrice;
    }

    @Override
    public String toString() {
        return "AnnualReport{" +
                "year=" + year +
                ", marketPlaceType='" + marketPlaceType + '\'' +
                ", bestListerEmailAddress='" + bestListerEmailAddress + '\'' +
                ", totalListingPrice=" + totalListingPrice +
                ", totalListingCount=" + totalListingCount +

                '}';
    }


}
