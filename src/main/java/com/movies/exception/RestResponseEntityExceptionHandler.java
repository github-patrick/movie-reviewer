package com.movies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = { ContentNotAllowedException.class})
    protected ApiError handleException(Exception ex, WebRequest request) {
        return new ApiError(new Timestamp(System.currentTimeMillis()).getTime(),HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), ex.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = { EmailExistsException.class})
    protected ApiError handleExceptionEmailExistsException(Exception ex, WebRequest request) {
        return new ApiError(new Timestamp(System.currentTimeMillis()).getTime(),HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), ex.getLocalizedMessage());
    }


}
