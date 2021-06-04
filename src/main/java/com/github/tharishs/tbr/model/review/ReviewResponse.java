package com.github.tharishs.tbr.model.review;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tharish Sooruth
 */
@Getter
@Setter
public class ReviewResponse implements Serializable {

    private static final long serialVersionUID = 6975014948562751841L;

    public String title;
    @JsonAlias("star_rating")
    public double starRating;
    public int count;
    public List<Item> items;
}
