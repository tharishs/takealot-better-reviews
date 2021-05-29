
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
    "identifier",
    "href",
    "link_data",
    "date_retrieved",
    "display",
    "type"
})
@Generated("jsonschema2pojo")
public class Meta implements Serializable
{

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
