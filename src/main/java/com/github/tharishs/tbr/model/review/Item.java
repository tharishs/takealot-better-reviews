package com.github.tharishs.tbr.model.review;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Tharish Sooruth
 */
@Getter
@Setter
public class Item implements Serializable {

    private static final long serialVersionUID = -7522568330234844934L;

    public String name;
    @JsonAlias("star_rating")
    public int starRating;
    public String review;
    public String date;
    public String location;
    public String id;
    @JsonAlias("sort_date")
    public String sortDate;
}
