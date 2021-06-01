package model;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Location {

    private UUID id;
    private String phone;
    private String country;
    private String town;

    @SerializedName("manager_name")
    private String managerName;

    @SerializedName("address_primary")
    private String addressPrimary;

    @SerializedName("address_secondary")
    private String addressSecondary;

    @SerializedName("postal_code")
    private String postalCode;

    public Location(UUID id, String managerName, String phone, String addressPrimary, String addressSecondary, String country, String town, String postalCode) {
        this.id = id;
        this.managerName = managerName;
        this.phone = phone;
        this.addressPrimary = addressPrimary;
        this.addressSecondary = addressSecondary;
        this.country = country;
        this.town = town;
        this.postalCode = postalCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressPrimary() {
        return addressPrimary;
    }

    public void setAddressPrimary(String addressPrimary) {
        this.addressPrimary = addressPrimary;
    }

    public String getAddressSecondary() {
        return addressSecondary;
    }

    public void setAddressSecondary(String addressSecondary) {
        this.addressSecondary = addressSecondary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", managerName='" + managerName + '\'' +
                ", phone='" + phone + '\'' +
                ", addressPrimary='" + addressPrimary + '\'' +
                ", addressSecondary='" + addressSecondary + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
