package com.clothingstore.product_service.services;

import com.clothingstore.product_service.models.Product;
import com.clothingstore.product_service.repositories.ProductRepository;
import com.clothingstore.product_service.repositories.*;
import com.clothingstore.product_service.models.*;
import com.clothingstore.product_service.models.ProductVariant;
import com.clothingstore.product_service.repositories.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;

    public List<ProductVariant> findByProductId(Integer productId){
        return productVariantRepository.findByProductId(productId);
    }

    public ProductVariant findById(Integer id){
        return productVariantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductVariant not found"));
    }

    public ProductVariant save(Integer productId, ProductVariant productVariant){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productVariant.setProduct(product);

        return productVariantRepository.save(productVariant);
    }
}
