package com.crealo.controller;

import com.crealo.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException(ProductNotFoundException ex) {
        log.debug("handling exception: {}", ex.getMessage(), ex);
        return status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
}
