package com.ecommerce.api;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class ApiResponse {
    private final boolean success;
    private final String message;
    
    public String getTimeStamp() {
        return LocalDateTime.now().toString();
    }
}