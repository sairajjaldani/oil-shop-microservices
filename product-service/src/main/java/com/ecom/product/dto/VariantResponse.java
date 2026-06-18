package com.ecom.product.dto;

import java.math.BigDecimal;

public record VariantResponse(

        Long id,

        String variantName,

        String size,

        BigDecimal price,

        Integer stockQuantity

) {
}