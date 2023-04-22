package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ResponseDto;
import com.ecommerce.dto.user.LoginDto;
import com.ecommerce.dto.user.LoginResponseDto;
import com.ecommerce.dto.user.SignUpDto;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
   
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignUpDto signUpDto) {
        System.out.println(signUpDto);
        
        return userService.signUp(signUpDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginDto loginDto ) {
        
        return userService.login(loginDto);
    }

}