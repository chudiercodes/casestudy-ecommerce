package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepo;

@Service
public class CategoryService {
    
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    
    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

    public void updateCategory(int categoryId, Category updateCategory) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new IllegalStateException("Category with id " + categoryId + " does not exist"));
        
        category.setName(updateCategory.getName());
        category.setDescription(updateCategory.getDescription());
        category.setImageUrl(updateCategory.getImageUrl());

        categoryRepo.save(category);
    }

    public boolean existsById(int categoryId) {
        return categoryRepo.existsById(categoryId);
    }
}