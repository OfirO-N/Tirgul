package com.catalog.oon.advice;

import com.catalog.oon.exceptions.InvalidEntityException;
import com.catalog.oon.exceptions.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kobis on 28 Nov, 2020
 */
@ControllerAdvice
@RestController
public class
RestExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> validationErrorHandler(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException ex) {
        return new ResponseEntity<>(ex.getAllErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { InvalidEntityException.class})
    public ResponseEntity<?> handleCustomException1(Exception ex){
         return ResponseEntity.badRequest()
                 .body(new ErrorDto("LOL",ex.getMessage(),HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(value = {InvalidOperationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto  handleCustomException2(InvalidOperationException ex) {
        String msg = ex.getMessage();
        return new ErrorDto("LOL", ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
