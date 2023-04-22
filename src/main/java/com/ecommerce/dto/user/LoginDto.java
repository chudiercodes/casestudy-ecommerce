package com.ecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class LoginDto {

    private String email;
    private String password;
    
}