package com.clothingstore.product_service.repositories;

import com.clothingstore.common.products.DTO.ProductVariantDTO;
import com.clothingstore.product_service.models.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
    List<ProductVariant> findVariantsByProductID(Integer ProductID);
}
