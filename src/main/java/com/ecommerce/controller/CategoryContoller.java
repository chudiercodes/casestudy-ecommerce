package com.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Category;
import com.ecommerce.service.categoryService;

@RestController
@RequestMapping("/category")
public class CategoryContoller {

    private final categoryService categoryService;
    
    public CategoryContoller(com.ecommerce.service.categoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public String createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        
        return "success";
    }

    @GetMapping("/list")
    public List<Category> getCategories() {
        
        return categoryService.getCategories();
    }
}