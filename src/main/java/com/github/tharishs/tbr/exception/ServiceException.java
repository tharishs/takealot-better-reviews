package com.github.tharishs.tbr.exception;

import com.github.tharishs.tbr.constant.ErrorEnum;

/**
 * @author Tharish Sooruth
 */
public class ServiceException extends BaseException {

    public ServiceException(ErrorEnum e) {
        super(e);
    }

    public ServiceException(ErrorEnum e, String additionalDesc) {
        super(e, additionalDesc);
    }

    public ServiceException(ErrorEnum e, Throwable t) {
        this(e, "", t);
    }

    public ServiceException(ErrorEnum e, String additionalDesc, Throwable t) {
        super(e, additionalDesc, t);
    }
}
