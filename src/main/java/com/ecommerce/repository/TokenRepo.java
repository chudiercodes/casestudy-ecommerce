package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.model.User;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken, Integer> {
    
    Optional<AuthenticationToken> findByUser(User user);
}