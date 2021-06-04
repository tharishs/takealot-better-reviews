package com.github.tharishs.tbr.model.detail;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tharish Sooruth
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "sku",
        "event",
        "quantity",
        "categoryname",
        "totalPrice",
        "productlineSku",
        "departmentname",
        "departmentid",
        "pageType",
        "categoryid",
        "prodid"
})
@Getter
public class DataLayer implements Serializable {

    @JsonProperty("name")
    public String name;
    @JsonProperty("sku")
    public Integer sku;
    @JsonProperty("event")
    public String event;
    @JsonProperty("quantity")
    public Integer quantity;
    @JsonProperty("categoryname")
    public List<String> categoryname = null;
    @JsonProperty("totalPrice")
    public Integer totalPrice;
    @JsonProperty("productlineSku")
    public String productlineSku;
    @JsonProperty("departmentname")
    public String departmentname;
    @JsonProperty("departmentid")
    public Integer departmentid;
    @JsonProperty("pageType")
    public String pageType;
    @JsonProperty("categoryid")
    public List<Integer> categoryid = null;
    @JsonProperty("prodid")
    public String prodid;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6611142077237987341L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
