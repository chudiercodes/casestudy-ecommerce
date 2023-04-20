package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Category;

@Repository
public interface categoryRepo extends JpaRepository<Category, Integer>{
    
}