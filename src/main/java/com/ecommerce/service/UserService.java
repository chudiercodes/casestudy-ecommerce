package com.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import javax.jws.soap.SOAPBinding.Use;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ResponseDto;
import com.ecommerce.dto.user.LoginDto;
import com.ecommerce.dto.user.LoginResponseDto;
import com.ecommerce.dto.user.SignUpDto;
import com.ecommerce.exception.CustomException;
import com.ecommerce.model.AuthenticationToken;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationService authService;

    @Transactional
    public ResponseDto signUp(SignUpDto signUpDto) {
		Optional<User> userByEmail = userRepo.findUserByEmail(signUpDto.getEmail());
        
		if(userByEmail.isPresent()) {
            User existingUser = userByEmail.get();
			throw new CustomException("a user already exists with that email: " + existingUser.getEmail());
		}

        //hash pwd
        String encryptedPwd = signUpDto.getPassword();

        try {
            encryptedPwd = hashPwd(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = new User(signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getEmail(), encryptedPwd);
        userRepo.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);

        //save token
        authService.saveCofirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("succes", "user created successfully");
        return responseDto;
	}

    private String hashPwd(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte[] digest = md.digest();
        String hash  = DatatypeConverter
                .printHexBinary(digest);

        return hash;
    }

    public LoginResponseDto login(LoginDto loginDto) {
        
        Optional<User> userOpt = userRepo.findUserByEmail(loginDto.getEmail());

        User user = userOpt.orElseThrow(() -> new CustomException("User does not exist with the email " + loginDto.getEmail()));

        //compare pwds
        try {
            if (!user.getPassword().equals(hashPwd(loginDto.getPassword()))) {
                throw new CustomException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) { 
            e.printStackTrace();
        }

        //if pwd matches grab the token of the user
        Optional<AuthenticationToken> optionalToken = authService.getToken(user);
        if(!optionalToken.isPresent()) {
            throw new CustomException("Token not found for user");
        }
        
        AuthenticationToken token = optionalToken.get();

        return new LoginResponseDto("success", token.getToken());
    }
}