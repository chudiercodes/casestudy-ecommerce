package com.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.api.ApiResponse;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.model.Wishlist;
import com.ecommerce.service.AuthenticationService;
import com.ecommerce.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    
    private final WishlistService wishlistService;

    private final AuthenticationService authService;

    public WishlistController(WishlistService wishlistService, AuthenticationService authService) {
        this.wishlistService = wishlistService;
        this.authService = authService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product, @RequestParam("token") String token) {
        
        //authenticate the token
        authService.authenticate(token);
        
        //find the user
        User user = authService.getUser(token);

        //save item to wishlist
        Wishlist wishList = new Wishlist(user, product);

        wishlistService.createWishlist(wishList);

        ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist ");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishlist(@PathVariable("token")String token) {
        //authenticate the token
        authService.authenticate(token);
        
        //find the user
        User user = authService.getUser(token);

        List<ProductDto> wishlistForUser = wishlistService.getWishlistForUser(user);

        return new ResponseEntity<>(wishlistForUser, HttpStatus.OK);
    }
}