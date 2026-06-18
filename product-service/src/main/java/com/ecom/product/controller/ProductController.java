package com.ecom.product.controller;

import com.ecom.product.dto.*;
import com.ecom.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request) {
    productService.createProduct(request);
    return ResponseEntity.status(HttpStatus.CREATED).body("Product Created Successfully");
    }

    @PostMapping("/{productId}/variants")
    public ResponseEntity<String> createVariant(@PathVariable Long productId, @RequestBody CreateVariantRequest request) {

        productService.createVariant(productId,request);

        return ResponseEntity.status(HttpStatus.CREATED).body("Variant Created Successfully");
    }


    @GetMapping("/{productId}/variants")
    public ResponseEntity<List<VariantResponse>> getVariantsByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getVariantsByProductId(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
    @PatchMapping("/variants/{variantId}/stock")
    public ResponseEntity<String> updateStock(@PathVariable Long variantId, @RequestBody UpdateStockRequest request) {
productService.updateStock(variantId,request);
        return ResponseEntity.ok("stock updated successfully");
    }
}
