package com.ecom.product.repository;

import com.ecom.product.entity.Product;
import com.ecom.product.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoductVariantRepository extends JpaRepository<ProductVariant,Long> {

    List<ProductVariant> findByProductId(Long productId);
}
