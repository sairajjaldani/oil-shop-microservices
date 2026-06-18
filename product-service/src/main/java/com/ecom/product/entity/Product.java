package com.ecom.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Builder.Default
    private Boolean active=true;

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
