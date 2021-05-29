
package com.github.tharishs.tbr.model.detail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "seller_id",
    "display_name",
    "fulfilled_by_takealot",
    "link_data"
})
@Generated("jsonschema2pojo")
public class SellerDetail implements Serializable
{

    @JsonProperty("seller_id")
    public Integer sellerId;
    @JsonProperty("display_name")
    public String displayName;
    @JsonProperty("fulfilled_by_takealot")
    public String fulfilledByTakealot;
    @JsonProperty("link_data")
    public LinkData__2 linkData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 19046717969028607L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
