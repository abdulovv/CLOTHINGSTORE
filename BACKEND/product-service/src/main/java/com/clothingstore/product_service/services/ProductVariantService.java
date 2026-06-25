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
import lombok.Data;
import lombok.NoArgsConstructor;
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
        Optional<List<ProductVariant>> prodVariantEntityOpt = Optional.ofNullable(productVariantRepository.findVariantsByProductID(productId));
        prodVariantEntityOpt.orElseThrow(() -> new RuntimeException("ProductVariants not found"));
        return productVariantMapper.toDTO(prodVariantEntityOpt.get());
    }

    @Transactional
    public ProductVariantDTO findProductVariantById(Integer id) {
        Optional<ProductVariant> prodVariantEntityOpt = productVariantRepository.findById(id);
        prodVariantEntityOpt.orElseThrow(() -> new RuntimeException("ProductVariants not found"));
        return productVariantMapper.toDTO(prodVariantEntityOpt.get());
    }

    @Transactional
    public ProductVariantDTO saveProductVariant(ProductVariantDTO productVariantDTO){
        ProductVariant prodVariantEntity = productVariantMapper.toEntity(productVariantDTO, new ProductVariant());
        Optional<Product> productEntityOpt = productRepository.findById(prodVariantEntity.getProductID());
        productEntityOpt.orElseThrow(() -> new RuntimeException("Product not found"));
        productVariantRepository.save(prodVariantEntity);
        return productVariantDTO;
    }

    public ProductVariantDTO updateProductVariantStock(Integer id, ProductVariantDTO productVariantDTO) {
        Optional<ProductVariant> prodVariantOpt = productVariantRepository.findById(id);
        prodVariantOpt.orElseThrow(() -> new RuntimeException("ProductVariant not found"));
        ProductVariant updatedProductVariant = prodVariantOpt.get();
        updatedProductVariant.setStock(productVariantDTO.getStock());
        productVariantRepository.save(updatedProductVariant);
        return productVariantDTO;
    }

    public void deleteProductVariant(Integer id){
        Optional<ProductVariant> prodVariantOpt = productVariantRepository.findById(id);
        ProductVariant toDeleteProdVariant = prodVariantOpt.orElseThrow(() -> new RuntimeException("Product variant not found"));
        productVariantRepository.delete(toDeleteProdVariant);
    }

    public void deleteAllByProductId(Integer productId){
        Optional<List<ProductVariant>> prodVariantsOpt = Optional.ofNullable(productVariantRepository.findVariantsByProductID(productId));
        List<ProductVariant> prodVariants = prodVariantsOpt.orElseThrow(() -> new RuntimeException("Product variants not found"));
        productVariantRepository.deleteAll(prodVariants);
    }
}
