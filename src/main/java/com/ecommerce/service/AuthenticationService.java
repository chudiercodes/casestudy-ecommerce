package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.repository.TokenRepo;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepo tokenRepo;

    public void saveCofirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken); 
    }
    
}