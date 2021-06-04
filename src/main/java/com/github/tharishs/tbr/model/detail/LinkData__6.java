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
        "path",
        "fields"
})
@Generated("jsonschema2pojo")
public class LinkData__6 implements Serializable {

    @JsonProperty("path")
    public String path;
    @JsonProperty("fields")
    public Fields__6 fields;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7841417952798225535L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
