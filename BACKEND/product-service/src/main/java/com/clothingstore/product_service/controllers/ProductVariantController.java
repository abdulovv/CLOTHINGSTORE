package com.clothingstore.product_service.controllers;

import com.clothingstore.common.products.DTO.ProductVariantDTO;
import com.clothingstore.product_service.services.ProductVariantService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @GetMapping("/{productID}/variants")
    public List<ProductVariantDTO> getAllProductVariant(@PathVariable("productID") Integer productId) {
        return productVariantService.findVariantsByProductId(productId);
    }

    @PostMapping("/variants")
    public ProductVariantDTO createProductVariant(@RequestBody ProductVariantDTO productVariantDTO)
    {
        return productVariantService.saveProductVariant(productVariantDTO);
    }

    @PatchMapping("/variants/{id}")
    public ProductVariantDTO updateProductVariantStock(@PathVariable("id") Integer id,
                                                       @RequestBody ProductVariantDTO productVariantDTO)
    {
        return productVariantService.updateProductVariantStock(id, productVariantDTO);
    }

    @DeleteMapping("/variants/{id}")
    public void deleteProductVariant(@PathVariable("id") Integer id){
        productVariantService.deleteProductVariant(id);
    }
}
