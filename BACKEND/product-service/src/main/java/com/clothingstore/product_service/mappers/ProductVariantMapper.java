package com.clothingstore.product_service.mappers;

import com.clothingstore.common.products.DTO.ProductVariantDTO;
import com.clothingstore.product_service.models.ProductVariant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductVariantMapper {

    public ProductVariantDTO toDTO(ProductVariant entity) {
        ProductVariantDTO dto = new ProductVariantDTO();
        dto.setId(entity.getId());
        dto.setProductID(entity.getProductID());
        dto.setSize(entity.getSize());
        dto.setStock(entity.getStock());
        return dto;
    }

    public List<ProductVariantDTO> toDTO(List<ProductVariant> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::toDTO).toList();
    }

    public ProductVariant toEntity(ProductVariantDTO dto, ProductVariant entity) {
        entity.setId(dto.getId());
        entity.setProductID(dto.getProductID());
        entity.setSize(dto.getSize());
        entity.setStock(dto.getStock());
        return entity;
    }
}
