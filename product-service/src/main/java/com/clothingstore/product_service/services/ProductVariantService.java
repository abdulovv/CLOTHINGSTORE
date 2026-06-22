package com.clothingstore.product_service.services;

import com.clothingstore.common.products.DTO.ProductVariantDTO;
import com.clothingstore.product_service.mappers.ProductVariantMapper;
import com.clothingstore.product_service.models.Product;
import com.clothingstore.product_service.repositories.ProductRepository;
import com.clothingstore.product_service.repositories.*;
import com.clothingstore.product_service.models.*;
import com.clothingstore.product_service.models.ProductVariant;
import com.clothingstore.product_service.repositories.ProductVariantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final ProductVariantMapper productVariantMapper;

    @Transactional
    public List<ProductVariantDTO> findVariantsByProductId(Integer productId){
        Optional<List<ProductVariant>> productVariantsEntityOpt = Optional.ofNullable(productVariantRepository.findVariantsByProductID(productId));
        productVariantsEntityOpt.orElseThrow(() -> new RuntimeException("ProductVariants not found"));
        return productVariantMapper.toDTO(productVariantsEntityOpt.get());
    }

    @Transactional
    public ProductVariantDTO findProductVariantById(Integer id) {
        Optional<ProductVariant> productVariantEntityOpt = productVariantRepository.findById(id);
        productVariantEntityOpt.orElseThrow(() -> new RuntimeException("ProductVariants not found"));
        return productVariantMapper.toDTO(productVariantEntityOpt.get());
    }

    @Transactional
    public ProductVariantDTO saveProductVariant(ProductVariantDTO productVariantDTO){
        ProductVariant productVariantEntity = productVariantMapper.toEntity(productVariantDTO, new ProductVariant());
        Optional<Product> productEntityOpt = productRepository.findById(productVariantEntity.getProductID());
        productEntityOpt.orElseThrow(() -> new RuntimeException("Product not found"));
        productVariantRepository.save(productVariantEntity);
        return productVariantDTO;
    }

    public ProductVariantDTO updateProductVariantStock(Integer id, ProductVariantDTO productVariantDTO) {
        Optional<ProductVariant> productVariantOpt = productVariantRepository.findById(id);
        productVariantOpt.orElseThrow(() -> new RuntimeException("ProductVariant not found"));
        ProductVariant updatedProductVariant = productVariantOpt.get();
        updatedProductVariant.setStock(productVariantDTO.getStock());
        productVariantRepository.save(updatedProductVariant);
        return productVariantDTO;
    }
}
