package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.User;
import com.ecommerce.model.Wishlist;
import com.ecommerce.repository.WishlistRepo;

@Service
public class WishlistService {
    
    // private final WishlistRepo wishlistRepo;

    // public WishlistService(WishlistRepo wishlistRepo) {
    //     this.wishlistRepo = wishlistRepo;
    // }
    
    @Autowired
    WishlistRepo wishlistRepo;

    @Autowired
    ProductService productService;
    
    public void createWishlist(Wishlist wishList) {
        wishlistRepo.save(wishList);
    }

    public List<ProductDto> getWishlistForUser(User user) {
        
        final List<Wishlist> wishlist = wishlistRepo.findAllByUser(user);

        List<ProductDto> products = new ArrayList<>();

        for(Wishlist product: wishlist) {
            products.add(productService.getProductDto(product.getProduct()));
        } 
        
        return products;
    }
} 