package com.ecom.product.exception;

public class VariantNotFoundException extends RuntimeException {
    public VariantNotFoundException(String message) {
        super(message);
    }
}
