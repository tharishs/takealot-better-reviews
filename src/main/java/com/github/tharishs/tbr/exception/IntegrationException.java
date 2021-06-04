package com.github.tharishs.tbr.exception;

import com.github.tharishs.tbr.constant.ErrorEnum;

/**
 * @author Tharish Sooruth
 */
public class IntegrationException extends BaseException {

    public IntegrationException(ErrorEnum e) {
        super(e);
    }

    public IntegrationException(ErrorEnum e, String additionalDesc) {
        super(e, additionalDesc);
    }

    public IntegrationException(ErrorEnum e, Throwable t) {
        this(e, "", t);
    }

    public IntegrationException(ErrorEnum e, String additionalDesc, Throwable t) {
        super(e, additionalDesc, t);
    }
}
