package com.github.tharishs.tbr.model.detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ProductDetails implements Serializable {

    @JsonProperty("title")
    public String title;
    @JsonProperty("desktop_href")
    public String desktopHref;
    @JsonProperty("core")
    public Core core;
    @JsonProperty("stock_availability")
    public StockAvailability stockAvailability;
    @JsonProperty("description")
    public Description description;
    @JsonProperty("seller_detail")
    public SellerDetail sellerDetail;
    @JsonProperty("meta")
    public Meta meta;
    @JsonProperty("data_layer")
    public DataLayer dataLayer;

}
