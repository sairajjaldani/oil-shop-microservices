package com.ecom.product.controller;

import com.ecom.product.dto.*;
import com.ecom.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Product APIs")
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create Product")
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

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest request) {
productService.updateProduct(productId,request);
return ResponseEntity.ok("Product Updated Successfully");
    }

    @PutMapping("/variants/{variantId}")
    public ResponseEntity<String> updateVariant(
            @PathVariable Long variantId,
            @RequestBody UpdateVariantRequest request) {

        productService.updateVariant(
                variantId,
                request);

        return ResponseEntity.ok(
                "Variant Updated Successfully");
    }

    @PatchMapping("/{productId}/deactivate")
    public ResponseEntity<String>
    deactivateProduct(
            @PathVariable Long productId) {

        productService.deactivateProduct(
                productId);

        return ResponseEntity.ok(
                "Product Deactivated Successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>>
    searchProducts(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                productService
                        .searchProducts(keyword));
    }
}
