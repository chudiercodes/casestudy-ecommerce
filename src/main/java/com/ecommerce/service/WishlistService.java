package com.ecommerce.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.User;
import com.ecommerce.model.Wishlist;
import com.ecommerce.repository.WishlistRepo;

@Service
public class WishlistService {
    
    private final WishlistRepo wishlistRepo;

    public WishlistService(WishlistRepo wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }
    
    public void createWishlist(Wishlist wishList) {
        wishlistRepo.save(wishList);
    }

    public ResponseEntity<List<ProductDto>> getWishlistForUser(User user) {
        return null;
    }
} 