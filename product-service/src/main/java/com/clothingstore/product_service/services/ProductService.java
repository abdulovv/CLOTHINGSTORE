package com.clothingstore.product_service.services;

import com.clothingstore.common.products.DTO.ProductDTO;
import com.clothingstore.product_service.mappers.ProductMapper;
import com.clothingstore.product_service.models.Product;
import com.clothingstore.product_service.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public List<ProductDTO> findAll() {
        Optional<List<Product>> productsEntities = Optional.of(productRepository.findAll());
        productsEntities.orElseThrow(() -> new RuntimeException("Products not found"));
        return productMapper.toDTO(productsEntities.get());
    }

    @Transactional
    public ProductDTO findById(Integer id) {
        Optional<Product> productEntityOpt = productRepository.findById(id);
        productEntityOpt.orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDTO(productEntityOpt.get());
    }

    @Transactional
    public ProductDTO saveProduct(Product product) {
        productRepository.save(product);
        return productMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO updatedProduct(Integer id, ProductDTO updatedProductDTO) {
        Optional<Product> productEntityOpt = productRepository.findById(id);
        Product toUpdateProduct = productEntityOpt.orElseThrow(() -> new RuntimeException("Product not found"));
        toUpdateProduct = productMapper.toEntity(updatedProductDTO, toUpdateProduct);
        productRepository.save(toUpdateProduct);
        return updatedProductDTO;
    }
}
