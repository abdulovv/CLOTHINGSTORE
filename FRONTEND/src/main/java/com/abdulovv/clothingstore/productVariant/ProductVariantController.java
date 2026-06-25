package com.abdulovv.clothingstore.productVariant;


import com.abdulovv.clothingstore.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RequestMapping("/api/products")
@RestController
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @GetMapping("/{productId}/variants")
    public List<ProductVariant> getAllProductVariants(@PathVariable Integer productId){
        return productVariantService.findByProductId(productId);
    }

    @PostMapping("/{productId}/variants")
    public ProductVariant createProductVariant(@PathVariable Integer productId,
                                               @RequestBody ProductVariant productVariant){
        return productVariantService.save(productId, productVariant);
    }
}
