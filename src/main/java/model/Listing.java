package model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Listing {

    private final UUID id;
    private final String title;
    private final String description;
    private final String currency;
    private final Integer quantity;
    private final Integer marketplace;

    @SerializedName("location_id")
    private final UUID inventoryItemLocationId;

    @SerializedName("listing_price")
    private final Float listingPrice;

    @SerializedName("listing_status")
    private final Integer listingStatus;

    @SerializedName("upload_time")
    private final String uploadTime;

    @SerializedName("owner_email_address")
    private final String ownerEmailAddress;

    private List<String> wrongFields = null;


    public Listing(UUID id, String title, String description, String currency, Integer quantity,
                   Integer marketplace, UUID inventoryItemLocationId, Float listingPrice,
                   Integer listingStatus, String uploadTime, String ownerEmailAddress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.currency = currency;
        this.quantity = quantity;
        this.marketplace = marketplace;
        this.inventoryItemLocationId = inventoryItemLocationId;
        this.listingPrice = listingPrice;
        this.listingStatus = listingStatus;
        this.uploadTime = uploadTime;
        this.ownerEmailAddress = ownerEmailAddress;
    }


    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getMarketplace() {
        return marketplace;
    }

    public UUID getInventoryItemLocationId() {
        return inventoryItemLocationId;
    }

    public Float getListingPrice() {
        return listingPrice;
    }

    public Integer getListingStatus() {
        return listingStatus;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public List<String> getWrongFields() {
        return wrongFields;
    }

    public boolean selfChecker() {

        List<String> wrongFields = new ArrayList<>();

        if (id == null)
            wrongFields.add("id");
        if (title == null) {
            wrongFields.add("title");
        }
        if (description == null) {
            wrongFields.add("description");
        }
        if (!ListingStatusType.getIdList().contains(listingStatus)) {
            wrongFields.add("listingStatus");
        }
        if (inventoryItemLocationId == null) {
            wrongFields.add("inventoryLocationId");
        }
        if ((listingPrice <= 0 || !(BigDecimal.valueOf(listingPrice).scale() > 2))) {
            wrongFields.add("listingPrice");
        }
        if ((currency == null || currency.length() != 3)) {
            wrongFields.add("currency");
        }
        if ((quantity == null || quantity <= 0)) {
            wrongFields.add("quantity");
        }
        if (listingStatus == null) {
            wrongFields.add("listingStatus");
        }
        if (marketplace == null) {
            wrongFields.add("marketplace");
        }
        if ((ownerEmailAddress == null || !validateEmailAddress(ownerEmailAddress))) {
            wrongFields.add("email");
        }

        this.wrongFields = wrongFields;

        return wrongFields.size() > 0;
    }

    private boolean validateEmailAddress(String emailAddress) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", quantity=" + quantity +
                ", marketplace=" + marketplace +
                ", inventoryItemLocationId=" + inventoryItemLocationId +
                ", listingPrice=" + listingPrice +
                ", listingStatus=" + listingStatus +
                ", uploadTime=" + uploadTime +
                ", ownerEmailAddress='" + ownerEmailAddress + '\'' +
                ", wrongFields='" + wrongFields + '\n' +
                '}';
    }
}
