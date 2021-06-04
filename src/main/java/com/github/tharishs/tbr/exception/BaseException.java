package com.github.tharishs.tbr.exception;

import com.github.tharishs.tbr.constant.ErrorEnum;

/**
 * @author Tharish Sooruth
 */
public class BaseException extends Exception {

    private final ErrorEnum errorEnum;
    private final int errorCode;
    private final String errorDesc;

    public BaseException(ErrorEnum e) {
        super(e.getErrorDescription());
        this.errorEnum = e;
        this.errorCode = e.getErrorCode();
        this.errorDesc = e.getErrorDescription();
    }

    public BaseException(ErrorEnum e, String additionalDesc) {
        super(e.getErrorDescription() + ". " + additionalDesc);
        this.errorEnum = e;
        this.errorCode = e.getErrorCode();
        this.errorDesc = e.getErrorDescription() + ". " + additionalDesc;
    }

    public BaseException(ErrorEnum e, Throwable t) {
        this(e, "", t);
    }

    public BaseException(ErrorEnum e, String additionalDesc, Throwable t) {
        super(e.getErrorDescription() + ". " + additionalDesc, t);
        this.errorEnum = e;
        this.errorCode = e.getErrorCode();
        this.errorDesc = e.getErrorDescription() + ". " + additionalDesc;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
