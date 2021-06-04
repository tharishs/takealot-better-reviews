package com.github.tharishs.tbr.model.detail;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tharish Sooruth
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "seller_id",
        "display_name",
        "fulfilled_by_takealot",
        "link_data"
})
@Generated("jsonschema2pojo")
public class Seller implements Serializable {

    @JsonProperty("seller_id")
    public Integer sellerId;
    @JsonProperty("display_name")
    public String displayName;
    @JsonProperty("fulfilled_by_takealot")
    public String fulfilledByTakealot;
    @JsonProperty("link_data")
    public LinkData__6 linkData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 945317136328850264L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
