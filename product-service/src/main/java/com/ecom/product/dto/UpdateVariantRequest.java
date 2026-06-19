package com.ecom.product.dto;

import java.math.BigDecimal;

public record UpdateVariantRequest(
        String variantName,

        String size,

        BigDecimal price
) {
}
