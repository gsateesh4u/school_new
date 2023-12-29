package com.amex.sms.school;

import com.amex.sms.school.exceptions.NotFoundException;
import com.amex.sms.school.model.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 27 Oct, 2023
 */
@ControllerAdvice
public class SchoolControllerAdvice {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<AppError> handleNotFoundException(NotFoundException ex, WebRequest request){
        AppError message = new AppError(HttpStatus.NOT_FOUND.toString(), Arrays.asList(ex.getMessage()));
        return new ResponseEntity<AppError>(message, HttpStatus.NOT_FOUND);
    }

    /*@ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<AppError> handleAccessDeniedException(AccessDeniedException ex, WebRequest request){
        AppError message = new AppError(HttpStatus.UNAUTHORIZED.toString(), Arrays.asList(ex.getMessage()));
        return new ResponseEntity<AppError>(message, HttpStatus.UNAUTHORIZED);
    }*/


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppError> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        AppError error = new AppError(HttpStatus.BAD_REQUEST.toString(), errors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppError> globalExceptionHandler(Exception ex, WebRequest request) {
        AppError message = new AppError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), Arrays.asList(ex.getMessage()));

        return new ResponseEntity<AppError>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
