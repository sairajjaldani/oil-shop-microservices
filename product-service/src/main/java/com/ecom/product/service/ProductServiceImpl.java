package com.ecom.product.service;

import com.ecom.product.dto.*;
import com.ecom.product.entity.Product;
import com.ecom.product.entity.ProductVariant;
import com.ecom.product.exception.ProductNotFoundException;
import com.ecom.product.exception.VariantNotFoundException;
import com.ecom.product.repository.ProductVariantRepository;
import com.ecom.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements  ProductService{

    private final ProductRepository productRepository;

    private final ProductVariantRepository productVariantRepository;

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

        List<Product> products=productRepository.findByActiveTrue();
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

    @Override
    public void updateProduct(Long productId, UpdateProductRequest request) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->
                new ProductNotFoundException("product not found with id: "+productId));

        product.setName(request.name());
        product.setDescription(request.description());
        productRepository.save(product);

    }

    @Override
    public void updateVariant(Long variantId, UpdateVariantRequest request) {
        ProductVariant variant=productVariantRepository.
                findById(variantId)
                .orElseThrow(()->new VariantNotFoundException("Variant not found with id: "+variantId));

        variant.setVariantName(request.variantName());
        variant.setSize(request.size());
        variant.setPrice(request.price());

        productVariantRepository.save(variant);
    }

    @Override
    public void deactivateProduct(Long productId) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->
                        new ProductNotFoundException("product not found with id: "+productId));

        product.setActive(false);

        productRepository.save(product);

    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription()
                )).toList();
    }


}
