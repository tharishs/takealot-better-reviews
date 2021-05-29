package com.github.tharishs.tbr.util;

import com.github.tharishs.tbr.constant.ErrorEnum;
import com.github.tharishs.tbr.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

}
