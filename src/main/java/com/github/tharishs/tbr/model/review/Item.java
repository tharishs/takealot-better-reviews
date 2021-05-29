package com.github.tharishs.tbr.model.review;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
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
