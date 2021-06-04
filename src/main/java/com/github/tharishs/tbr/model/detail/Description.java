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
        "tab_title",
        "html"
})
@Generated("jsonschema2pojo")
public class Description implements Serializable {

    @JsonProperty("tab_title")
    public String tabTitle;
    @JsonProperty("html")
    public String html;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8192135813847886181L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
