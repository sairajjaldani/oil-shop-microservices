package com.ecom.product.service;

import com.ecom.product.dto.*;
import com.ecom.product.entity.Product;
import com.ecom.product.entity.ProductVariant;
import com.ecom.product.exception.ProductNotFoundException;
import com.ecom.product.exception.VariantNotFoundException;
import com.ecom.product.repository.PoductVariantRepository;
import com.ecom.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService{

    private final ProductRepository productRepository;

    private final PoductVariantRepository productVariantRepository;

    @Override
    public void createProduct(CreateProductRequest request) {

        Product product= Product.builder()
                .name(request.name())
                .description(request.description())
                .build();

        productRepository.save(product);

    }

    @Override
    public void createVariant(Long productId, CreateVariantRequest request) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: "+productId));

        ProductVariant productVariant=ProductVariant.builder()
                .variantName(request.variantName())
                .size(request.size())
                .price(request.price())
                .stockQuantity(request.stockQuantity())
                .product(product)
                .build();

        productVariantRepository.save(productVariant);
    }

    @Override
    public List<VariantResponse> getVariantsByProductId(Long productId) {

        List<ProductVariant> variants=productVariantRepository
                .findByProductId(productId);

        return variants.stream().map(variant->
                new VariantResponse(
                variant.getId(),
                        variant.getVariantName(),
                        variant.getSize(),
                        variant.getPrice(),
                        variant.getStockQuantity()
        )).toList();
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products=productRepository.findAll();
        return products.stream().map(product->
                        new ProductResponse(
                                product.getId(),
                                product.getName(),
                                product.getDescription()
                        )).toList();
    }

    @Override
    public ProductResponse getProductById(Long productId) {

        Product product=productRepository
                .findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found with id: "+productId));
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription()
        );
    }

    @Override
    public void updateStock(Long variantId, UpdateStockRequest request) {
        ProductVariant variant=productVariantRepository
                .findById(variantId)
                .orElseThrow(()->
                        new VariantNotFoundException("Variant not found with id:"+variantId));
        variant.setStockQuantity(request.stockQuantity());

        productVariantRepository.save(variant);
    }


}
