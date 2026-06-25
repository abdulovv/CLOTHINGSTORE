package com.clothingstore.common.products.DTO;

import com.clothingstore.common.products.enums.Size;
import lombok.Data;

@Data
public class ProductVariantDTO {
    private Integer id;
    private Integer productID;
    private Size size;
    private Integer stock;

    public ProductVariantDTO() {
    }

    public ProductVariantDTO(Integer id, Integer productID, Size size, Integer stock) {
        this.id = id;
        this.productID = productID;
        this.size = size;
        this.stock = stock;
    }
}
