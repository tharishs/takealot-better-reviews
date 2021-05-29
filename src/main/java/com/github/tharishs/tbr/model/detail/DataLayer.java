
package com.github.tharishs.tbr.model.detail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

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
public class DataLayer implements Serializable
{

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
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
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
