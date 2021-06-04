package com.github.tharishs.tbr.config;

import com.github.tharishs.tbr.constant.ErrorEnum;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tharish Sooruth
 */
@ControllerAdvice(annotations = {RestController.class})
@RestController
@Slf4j
public class ExceptionHandlerConfig {

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> serviceError(ServiceException ex) {
        return new ResponseEntity<>(convertServiceError(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> genericError(Exception ex) {
        return new ResponseEntity<>(convertGenericError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse convertServiceError(ServiceException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex);
        log.error(errorResponse.toString(), ex);
        return errorResponse;
    }

    private ErrorResponse convertGenericError(Exception ex) {

        ErrorResponse errorResponse = new ErrorResponse(ErrorEnum.UNDEFINED);
        String errorMessage = ex.getMessage() != null ? ex.getMessage() : ex.toString();
        errorResponse.setErrorMessage(errorMessage);
        log.error(errorResponse.toString(), ex);

        return errorResponse;
    }

}
