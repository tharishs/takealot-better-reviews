package com.github.tharishs.tbr.model.review;

import com.github.tharishs.tbr.util.lucene.Keyword;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Tharish Sooruth
 */
@Getter
@Setter
public class ReviewKeyword {

    private final String term;
    private final Integer frequency;
    private final Integer rank;
    private final Double percentageOccurrence;

    public ReviewKeyword(Keyword keyword, Integer totalSize, Integer rank) {

        this.term = keyword.getStem();
        this.frequency = keyword.getFrequency();
        this.rank = rank;
        this.percentageOccurrence = (frequency / (double) totalSize) * 100;
    }

}
