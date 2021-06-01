package model;

import com.google.gson.annotations.SerializedName;

public class Marketplace {

    private Integer id;

    @SerializedName("marketplace_name")
    private String marketplaceName;

    public Marketplace(Integer id, String marketplaceName) {
        this.id = id;
        this.marketplaceName = marketplaceName;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
    }
}
