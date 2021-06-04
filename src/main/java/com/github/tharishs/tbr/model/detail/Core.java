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
        "id",
        "title",
        "subtitle",
        "brand",
        "brand_url",
        "reviews",
        "star_rating",
        "slug",
        "authors",
        "formats"
})
@Generated("jsonschema2pojo")
public class Core implements Serializable {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("subtitle")
    public String subtitle;
    @JsonProperty("brand")
    public String brand;
    @JsonProperty("brand_url")
    public BrandUrl brandUrl;
    @JsonProperty("reviews")
    public Integer reviews;
    @JsonProperty("star_rating")
    public Float starRating;
    @JsonProperty("slug")
    public String slug;
    @JsonProperty("authors")
    public List<Object> authors = null;
    @JsonProperty("formats")
    public List<Object> formats = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4358948637424782418L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
