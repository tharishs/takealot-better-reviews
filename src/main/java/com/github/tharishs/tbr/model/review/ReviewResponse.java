package com.github.tharishs.tbr.model.review;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewResponse {

    public String title;
    @JsonAlias("star_rating")
    public double starRating;
    public int count;
    public List<Item> items;
}
