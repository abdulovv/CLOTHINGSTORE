package com.clothingstore.product_service.controllers;


import com.clothingstore.product_service.models.ProductVariant;
import com.clothingstore.product_service.services.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/api/products")
@RestController
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @GetMapping("/{productId}/variants")
    public List<ProductVariant> getAllProductVariants(@PathVariable("productId") Integer productId) {
        return productVariantService.findByProductId(productId);
    }

    @PostMapping("/{productId}/variants")
    public ProductVariant createProductVariant(@PathVariable("productId") Integer productId,
                                               @RequestBody ProductVariant productVariant)
    {
        return productVariantService.save(productId, productVariant);
    }
}
