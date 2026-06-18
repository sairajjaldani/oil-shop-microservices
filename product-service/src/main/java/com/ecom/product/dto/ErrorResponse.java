package com.ecom.product.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        String message,
        Integer status
) {
}