package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.AuthenticationToken;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
    
}