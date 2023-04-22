package com.ecommerce.dto.user;

import lombok.Data;

public @Data class SignUpDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    
    public SignUpDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}