package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.ApiResponse;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryContoller {

    private final CategoryService categoryService;
    
    public CategoryContoller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        
        return new ResponseEntity<>(new ApiResponse(true, "a new category has been created"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Category> getCategories() {
        
        return categoryService.getCategories();
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category) {
        if(!categoryService.existsById(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }

        categoryService.updateCategory(categoryId, category);

        return new ResponseEntity<>(new ApiResponse(true, "a category has been updated"), HttpStatus.OK);
    }
}