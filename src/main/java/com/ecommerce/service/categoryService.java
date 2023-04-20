package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.dao.categoryRepo;
import com.ecommerce.model.Category;

@Service
public class categoryService {
    
    private final categoryRepo categoryRepo;

    public categoryService(categoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    
    public void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }

}