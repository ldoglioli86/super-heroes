package com.superheroes.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;
import java.util.Objects;

public class HasExceptionHandlers {
    public static final String TRACE = "trace";

    @Value("${reflectoring.trace:false}")
    private boolean printStackTrace;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception exception,
            WebRequest request){
        return buildErrorResponse(
                exception,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(
            NoSuchElementException exception,
            WebRequest request
    ){
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleItemNotFoundInDBException(
            EmptyResultDataAccessException exception,
            WebRequest request
    ){
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            JpaObjectRetrievalFailureException exception,
            WebRequest request
    ){
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.LENGTH_REQUIRED)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentExceptionException(
            IllegalArgumentException exception,
            WebRequest request
    ){
        return buildErrorResponse(exception, HttpStatus.LENGTH_REQUIRED, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                exception.getMessage()
        );

        if(printStackTrace && isTraceOn(request)){
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String [] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }
}
