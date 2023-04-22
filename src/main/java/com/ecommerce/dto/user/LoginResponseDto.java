package com.ecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class LoginResponseDto {
    
    private String status;
    private String token;
}