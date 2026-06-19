package com.ecom.product.service;

import com.ecom.product.dto.*;
import com.ecom.product.entity.Product;

import java.util.List;

public interface ProductService {

    void createProduct(CreateProductRequest request);

    void createVariant(Long productId,CreateVariantRequest request);

    List<VariantResponse> getVariantsByProductId(Long productId);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long productId);

    void updateStock(Long variantId, UpdateStockRequest request);

    void updateProduct(Long productId,UpdateProductRequest request);

    void updateVariant(Long variantId,UpdateVariantRequest request);

    void deactivateProduct(Long productId);

    List<ProductResponse> searchProducts(String keyword);


}
