package com.github.tharishs.tbr.model.detail;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tharish Sooruth
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "is_leadtime",
        "is_imported",
        "distribution_centres",
        "when_do_i_get_it_text",
        "when_do_i_get_it_info",
        "display_seasonal_message",
        "seasonal_message_text",
        "seasonal_message_info"
})
@Generated("jsonschema2pojo")
public class StockAvailability implements Serializable {

    @JsonProperty("status")
    public String status;
    @JsonProperty("is_leadtime")
    public Boolean isLeadtime;
    @JsonProperty("is_imported")
    public Boolean isImported;
    @JsonProperty("distribution_centres")
    public List<DistributionCentre> distributionCentres = null;
    @JsonProperty("when_do_i_get_it_text")
    public String whenDoIGetItText;
    @JsonProperty("when_do_i_get_it_info")
    public String whenDoIGetItInfo;
    @JsonProperty("display_seasonal_message")
    public Boolean displaySeasonalMessage;
    @JsonProperty("seasonal_message_text")
    public Object seasonalMessageText;
    @JsonProperty("seasonal_message_info")
    public Object seasonalMessageInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4707007903669574103L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
