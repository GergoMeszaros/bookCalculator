package model;

import com.google.gson.annotations.SerializedName;

public class ListingStatus {

    private Integer id;

    @SerializedName("status_name")
    private String statusName;


    public ListingStatus(Integer id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
