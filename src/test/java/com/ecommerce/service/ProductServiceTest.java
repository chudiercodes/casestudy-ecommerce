package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepo;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    
    @Mock
    private ProductRepo productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product 1", "http://example.com/image1.jpg", 9.99, "Product 1 description"));
        productList.add(new Product("Product 2", "http://example.com/image2.jpg", 19.99, "Product 2 description"));
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        
        List<ProductDto> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("http://example.com/image2.jpg", result.get(1).getImageUrl());
    }
}