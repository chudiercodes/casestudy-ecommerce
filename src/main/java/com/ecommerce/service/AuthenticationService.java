package com.ecommerce.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.AuthenticationFailedException;
import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.model.User;
import com.ecommerce.repository.TokenRepo;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepo tokenRepo;

    public void saveCofirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken); 
    }

    public Optional<AuthenticationToken> getToken(User user) {
        return tokenRepo.findByUser(user);
    }

    public User getUser(String token) {
        final Optional<AuthenticationToken> authToken = tokenRepo.findByToken(token);

        if(!authToken.isPresent()) {
            throw new AuthenticationFailedException("authentication failed");
        }

        return authToken.get().getUser();
    }

    public void authenticate(String token) throws AuthenticationFailedException {
        if(Objects.isNull(token)) {
            throw new AuthenticationFailedException("token not present");
        }
    
        User user = getUser(token);
    
        if(Objects.isNull(user)) {
            throw new AuthenticationFailedException("token not valid");
        }
    }
}