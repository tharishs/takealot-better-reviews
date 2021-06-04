package com.github.tharishs.tbr.util;

import com.github.tharishs.tbr.constant.ErrorEnum;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.util.lucene.Keyword;
import com.github.tharishs.tbr.util.lucene.KeywordFinder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tharish Sooruth
 */
@Slf4j
public class StringUtil {

    /**
     * Given any string (typically a takealot url) extract the PLID from it.
     *
     * @param text takealot URL (but can be any string that contains a valid PLID)
     * @return Takealot PLID
     * @throws ServiceException if PLID is invalid
     */
    public static String extractPlid(String text) throws ServiceException {
        if (StringUtils.isBlank(text)) {
            throw new ServiceException(ErrorEnum.INVALID_URL, text);
        }

        Matcher matcher = Pattern.compile("(PLID|plid)[\\d]+").matcher((text));
        if (matcher.find()) {
            return matcher.group();
        }

        throw new ServiceException(ErrorEnum.INVALID_PLID, text);
    }

    /**
     * Returns a list of keywords from a given String
     *
     * @param input String input
     * @return List of keywords
     * @see Keyword
     */
    public static List<Keyword> findKeywords(String input) {
        try {
            return KeywordFinder.guessFromString(input);
        } catch (IOException e) {
            log.error("Unable to obtain keywords for {}", input);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
