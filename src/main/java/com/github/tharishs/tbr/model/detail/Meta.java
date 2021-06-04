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
        "identifier",
        "href",
        "link_data",
        "date_retrieved",
        "display",
        "type"
})
@Generated("jsonschema2pojo")
public class Meta implements Serializable {

    @JsonProperty("identifier")
    public String identifier;
    @JsonProperty("href")
    public String href;
    @JsonProperty("link_data")
    public LinkData__3 linkData;
    @JsonProperty("date_retrieved")
    public String dateRetrieved;
    @JsonProperty("display")
    public Boolean display;
    @JsonProperty("type")
    public String type;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8723610129904363517L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
