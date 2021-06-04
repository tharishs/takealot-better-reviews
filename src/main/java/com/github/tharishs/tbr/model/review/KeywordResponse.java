package com.github.tharishs.tbr.model.review;

import com.github.tharishs.tbr.util.lucene.Keyword;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tharish Sooruth
 */
@Getter
@Setter
public class KeywordResponse {

    private final List<ReviewKeyword> reviewKeywords = new ArrayList<>();
    private final String plid;
    private Integer keywordCount = 0;

    public KeywordResponse(String plid, List<Keyword> keywords) {
        this.plid = plid;
        List<Keyword> filteredKeywords = keywords.stream().filter(i -> i.getFrequency() > 1).collect(Collectors.toList());
        int i = 1;
        for (Keyword key : filteredKeywords) {
            this.reviewKeywords.add(new ReviewKeyword(key, filteredKeywords.size(), i));
            i++;
        }
        this.keywordCount = this.reviewKeywords.size();
    }
}
