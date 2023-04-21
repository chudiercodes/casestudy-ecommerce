package com.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class ProductDto {
    
    private int id;
    private String name;
    private String imageUrl;
    private double price;
    private String description;
    private int categoryId;
   
    public ProductDto(int id, String name, String imageUrl, double price, String description, int categoryId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    
}