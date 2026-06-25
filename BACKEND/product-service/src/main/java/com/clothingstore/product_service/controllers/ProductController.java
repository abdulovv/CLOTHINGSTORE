package com.clothingstore.product_service.controllers;

import com.clothingstore.common.products.DTO.ProductDTO;
import com.clothingstore.product_service.models.Product;
import com.clothingstore.product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")

@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO product, @PathVariable("id") Integer id) {
        return productService.updatedProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ProductDTO deleteProduct(@PathVariable("id") Integer id) {
        return productService.deleteProduct(id);
    }
}
