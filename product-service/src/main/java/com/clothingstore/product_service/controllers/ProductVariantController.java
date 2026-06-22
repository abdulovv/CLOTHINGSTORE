package com.clothingstore.product_service.controllers;

import com.clothingstore.common.products.DTO.ProductVariantDTO;
import com.clothingstore.product_service.services.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor

@RequestMapping("/api")
@RestController
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @GetMapping("/products/{productID}/variants")
    public List<ProductVariantDTO> getAllProductVariant(@PathVariable("productID") Integer productId) {
        return productVariantService.findVariantsByProductId(productId);
    }

    @PostMapping("/products/variants")
    public ProductVariantDTO createProductVariant(@RequestBody ProductVariantDTO productVariantDTO)
    {
        return productVariantService.saveProductVariant(productVariantDTO);
    }

    @PatchMapping("/products/variants/{id}")
    public ProductVariantDTO updateProductVariantStock(@PathVariable("id") Integer id,
                                                       @RequestBody ProductVariantDTO productVariantDTO)
    {
        return productVariantService.updateProductVariantStock(id, productVariantDTO);
    }
}
