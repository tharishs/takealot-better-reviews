package com.github.tharishs.tbr.constant;

public enum ErrorEnum {

    UNDEFINED(-1, "An undefined error occurred"),
    INVALID_URL(100, "The URL provided is invalid"),
    INVALID_PLID(101, "The PLID provided is invalid"),
    INTEGRATION_FAILED_ACCESS(102, "Failed to connect to URL"),
    INTEGRATION_NO_CONTENT(103, "No content received from the requested resource"),
    INTEGRATION_FORBIDDEN(104, "Forbidden access to the requested resource"),
    INTEGRATION_NOT_FOUND(105, "The requested resource was not found"),
    INTEGRATION_UNAUTHORIZED(106, "Unauthorized access to the requested resource"),
    INTEGRATION_UNDEFINED_ERROR(107, "The requested resource gave an undefined error");

    private final int errorCode;
    private final String errorDescription;

    ErrorEnum(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

}
