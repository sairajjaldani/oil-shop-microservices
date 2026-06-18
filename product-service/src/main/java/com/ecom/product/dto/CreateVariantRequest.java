package com.ecom.product.dto;

import java.math.BigDecimal;

public record CreateVariantRequest(
        String variantName,
        String size,
        BigDecimal price,
        Integer stockQuantity
) {
}
