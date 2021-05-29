package com.github.tharishs.tbr.constant;

public enum ErrorEnum {

    UNDEFINED(-1, "An undefined error occurred"),
    INVALID_URL(100, "The URL provided is invalid"),
    INVALID_PLID(101, "The PLID provided is invalid"),
    INVALID_STAR(102, "The star rating must be between 1 and 5"),
    INTEGRATION_FAILED_ACCESS(201, "Failed to connect to URL"),
    INTEGRATION_NO_CONTENT(202, "No content received from the requested resource"),
    INTEGRATION_FORBIDDEN(203, "Forbidden access to the requested resource"),
    INTEGRATION_NOT_FOUND(204, "The requested resource was not found"),
    INTEGRATION_UNAUTHORIZED(205, "Unauthorized access to the requested resource"),
    INTEGRATION_UNDEFINED_ERROR(206, "The requested resource gave an undefined error");

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
