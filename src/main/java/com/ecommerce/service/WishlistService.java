package com.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.repository.WishlistRepo;

@Service
public class WishlistService {
    
    private final WishlistRepo wishlistRepo;

    public WishlistService(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }
    
}