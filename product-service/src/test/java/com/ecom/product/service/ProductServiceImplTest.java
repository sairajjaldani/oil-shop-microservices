package com.ecom.product.service;

import com.ecom.product.dto.CreateProductRequest;
import com.ecom.product.dto.CreateVariantRequest;
import com.ecom.product.dto.ProductResponse;
import com.ecom.product.dto.UpdateStockRequest;
import com.ecom.product.entity.Product;
import com.ecom.product.entity.ProductVariant;
import com.ecom.product.exception.ProductNotFoundException;
import com.ecom.product.exception.VariantNotFoundException;
import com.ecom.product.repository.ProductVariantRepository;
import com.ecom.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    ProductVariantRepository productVariantRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @Test
    void createProduct_ShouldSaveSuccessfully() {

        CreateProductRequest product = new CreateProductRequest(
                "Groundnut Oil",
                "Wood Pressed oil"
        );

        productServiceImpl.createProduct(product);

        Mockito.verify(productRepository).save(Mockito.any(Product.class));
    }

    @Test
    void getProductById_Success() {

        Product product = new Product();
        product.setId(1L);
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        ProductResponse response = productServiceImpl.getProductById(1L);
        Assertions.assertEquals(product.getId(), response.id());
    }

    @Test
    void getProductById_ProductNotFound() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductNotFoundException.class, () -> productServiceImpl.getProductById(1L));
    }

    @Test
    void createVariant_ShouldSaveSuccessfully() {

        Product product = new Product();
        product.setId(1L);
        CreateVariantRequest variant = new CreateVariantRequest(
                "groundnut oil 5L", "5L", BigDecimal.valueOf(2699), 10);
        Mockito.when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        productServiceImpl.createVariant(1L, variant);
        Mockito.verify(productVariantRepository).save(Mockito.any(ProductVariant.class));
    }

    @Test
    void updateStock_Success() {

        ProductVariant variant = new ProductVariant();
        variant.setId(1L);


        UpdateStockRequest request = new UpdateStockRequest(10);

        Mockito.when(productVariantRepository.findById(variant.getId())).thenReturn(Optional.of(variant));

        productServiceImpl.updateStock(variant.getId(), request);

        Mockito.verify(productVariantRepository).save(Mockito.any(ProductVariant.class));

        Assertions.assertEquals(10, variant.getStockQuantity());

    }

    @Test
    void updateStock_VariantNotFound() {
        UpdateStockRequest request = new UpdateStockRequest(10);
        Mockito.when(productVariantRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(VariantNotFoundException.class, () -> productServiceImpl.updateStock(1L, request));
    }

    @Test
    void getAllProducts() {

        Product p1 = new Product();
        p1.setName("Groundnut oil");
        p1.setDescription("Wood pressed");
        p1.setActive(true);
        Product p2 = new Product();
        p2.setName("sesame oil");
        p2.setDescription("Wood pressed");
        p2.setActive(true);

        List<Product> productList = List.of(p1, p2);

        Mockito.when(productRepository.findByActiveTrue()).thenReturn(productList);

        List<ProductResponse> response = productServiceImpl.getAllProducts();
        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals("Groundnut oil", response.get(0).name());

    }
}