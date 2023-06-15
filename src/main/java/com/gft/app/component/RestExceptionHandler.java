package com.gft.app.component;

import com.gft.app.server.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        Error error = getError(ex, HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        Error error = getError(ex, HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExistsException(EntityExistsException ex) {
        Error error = getError(ex, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestClientException.class)
    protected ResponseEntity<Object> handleRestClientException(RestClientException ex) {
        Error error = getError(ex, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        Error error = getError(ex, ex.getStatus().toString());
        return new ResponseEntity(error, ex.getStatus());
    }

    public Error getError(Exception ex, String code) {
        log.error(ex.getMessage(), ex);
        Error error = new Error();
        error.setCode(code);
        error.setMessage(ex.getMessage());
        return error;
    }

}
