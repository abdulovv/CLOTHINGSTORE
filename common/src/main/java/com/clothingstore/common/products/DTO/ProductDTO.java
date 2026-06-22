package com.clothingstore.common.products.DTO;
import com.clothingstore.common.products.enums.Category;
import com.clothingstore.common.products.enums.Sex;
import lombok.Data;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Sex sex;
    private Category category;
    private String imagePath;
}
