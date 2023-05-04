package com.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.User;
import com.ecommerce.model.Wishlist;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
    
    //order by created date descending
    List<Wishlist> findAllByUser(User user);
}