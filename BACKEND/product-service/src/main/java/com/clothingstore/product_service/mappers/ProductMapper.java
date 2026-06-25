package com.clothingstore.product_service.mappers;

import com.clothingstore.common.products.DTO.ProductDTO;
import com.clothingstore.common.products.DTO.ProductVariantDTO;
import com.clothingstore.product_service.models.Product;
import com.clothingstore.product_service.models.ProductVariant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public ProductDTO toDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setImagePath(entity.getImagePath());
        dto.setCategory(entity.getCategory());
        dto.setSex(entity.getSex());
        return dto;
    }

    public List<ProductDTO> toDTO(List<Product> entities) {
        List<ProductDTO> dtos = new ArrayList<>();
        for (Product entity : entities){
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    public Product toEntity(ProductDTO dto, Product entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setImagePath(dto.getImagePath());
        entity.setCategory(dto.getCategory());
        entity.setSex(dto.getSex());
        return entity;
    }
}
