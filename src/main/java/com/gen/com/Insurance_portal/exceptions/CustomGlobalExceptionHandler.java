package com.gen.com.Insurance_portal.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(new Date()));
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundEntityException.class})
    public ResponseEntity<Object> handleNotFoundEntityException(
            NotFoundEntityException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(new Date()));
        body.put("message", ex.getMessage());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MessageException.class})
    public ResponseEntity<Object> handleMessageException(
            MessageException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(new Date()));
        body.put("message", ex.getMessage());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TokenRefreshException.class})
    public ResponseEntity<Object> handleTokenRefreshException(
            TokenRefreshException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(new Date()));
        body.put("message", ex.getMessage());
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ClaimsException.class})
    public ResponseEntity<Object> handleMessageException(
            ClaimsException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.BAD_REQUEST);
        body.put("timestamp", new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").format(new Date()));
        body.put("message", ex.getMessage());
        body.put("errors", ex.getMessages());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}