package com.ecom.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_variants")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String variantName;

    private String size;

    private BigDecimal price;

    private Integer stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt=LocalDateTime.now();
        updatedAt=LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt=LocalDateTime.now();
    }

}
