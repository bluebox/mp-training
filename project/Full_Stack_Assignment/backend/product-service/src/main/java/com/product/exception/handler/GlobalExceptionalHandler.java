package com.product.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.product.Exceptions.ProductNotFoundException;
import com.product.Exceptions.RequestNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException e) {
        log.error("Product not found: {}", e.getMessage(), e);
        return ResponseEntity.status(404).body(e.getMessage());
    }

    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<String> handleRequestNotFound(RequestNotFoundException e) {
        log.error("Request not found: {}", e.getMessage(), e);
        return ResponseEntity.status(404).body(e.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> globalException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(500).body("Unable to proccess your reuqest");
    }

}
