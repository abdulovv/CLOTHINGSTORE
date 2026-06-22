package com.clothingstore.product_service.models;

import com.clothingstore.common.products.enums.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "product_variants", schema = "product_schema")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_id")
    private Integer productID;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private Size size;

    @Column(name = "stock")
    private Integer stock;
}