package com.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class ResponseDto {
    
    private String status;
    private String message;
}