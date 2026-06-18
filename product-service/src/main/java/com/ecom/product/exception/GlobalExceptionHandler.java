package com.ecom.product.exception;

import com.ecom.product.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleProductNotFoundException(
            ProductNotFoundException ex) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value()
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(VariantNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    handleVariantNotFoundException(
            VariantNotFoundException ex) {

        ErrorResponse errorResponse =
                new ErrorResponse(
                        LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND.value()
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}