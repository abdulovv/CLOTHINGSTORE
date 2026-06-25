package com.abdulovv.clothingstore.productVariant;

import com.abdulovv.clothingstore.product.Product;
import com.abdulovv.clothingstore.product.ProductRepository;
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
