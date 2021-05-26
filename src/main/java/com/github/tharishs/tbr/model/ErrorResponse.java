package com.github.tharishs.tbr.model;

import com.github.tharishs.tbr.constant.ErrorEnum;
import com.github.tharishs.tbr.exception.BaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ErrorResponse {

    private String errorReference;
    private Timestamp errorTime;
    private int errorCode;
    private String errorMessage;

    public ErrorResponse(String errorReference, Timestamp errorTime, int errorCode, String errorMessage) {
        this.errorReference = errorReference;
        this.errorTime = errorTime;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(BaseException exception) {

        this(UUID.randomUUID().toString(),
                new Timestamp(new Date().getTime()),
                exception.getErrorCode(),
                exception.getErrorDesc());
    }

    public ErrorResponse(ErrorEnum errorEnum) {

        this(UUID.randomUUID().toString(),
                new Timestamp(new Date().getTime()),
                errorEnum.getErrorCode(),
                errorEnum.getErrorDescription());
    }
}
