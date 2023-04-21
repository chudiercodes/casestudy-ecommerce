package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepo;

@Service
public class ProductService {
    
    @Autowired
    ProductRepo productRepo;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();

        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        productRepo.save(product);
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());

        if (product.getCategory() != null) {
            productDto.setCategoryId(product.getCategory().getCategoryId());
        }

        productDto.setId(product.getProductId());

        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: allProducts) {
            productDtos.add(getProductDto(product));
        }
        
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, int productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
    
        productRepo.save(product);
    }
    
}