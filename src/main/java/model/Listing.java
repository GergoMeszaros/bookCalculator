package model;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class Listing {

    private UUID id;
    private String title;
    private String description;
    private String currency;
    private Integer quantity;
    private Integer marketplace;

    @SerializedName("inventory_item_location_id")
    private UUID inventoryItemLocationId;

    @SerializedName("listing_price")
    private Integer listingPrice;

    @SerializedName("listing_status")
    private Integer listingStatus;

    @SerializedName("upload_time")
    private Date uploadTime;

    @SerializedName("owner_email_address")
    private String ownerEmailAddress;


    public Listing(UUID id, String title, String description, String currency, Integer quantity, Integer marketplace, UUID inventoryItemLocationId, Integer listingPrice, Integer listingStatus, Date uploadTime, String ownerEmailAddress) {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Integer marketplace) {
        this.marketplace = marketplace;
    }

    public UUID getInventoryItemLocationId() {
        return inventoryItemLocationId;
    }

    public void setInventoryItemLocationId(UUID inventoryItemLocationId) {
        this.inventoryItemLocationId = inventoryItemLocationId;
    }

    public Integer getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(Integer listingPrice) {
        this.listingPrice = listingPrice;
    }

    public Integer getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(Integer listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getOwnerEmailAddress() {
        return ownerEmailAddress;
    }

    public void setOwnerEmailAddress(String ownerEmailAddress) {
        this.ownerEmailAddress = ownerEmailAddress;
    }
}
