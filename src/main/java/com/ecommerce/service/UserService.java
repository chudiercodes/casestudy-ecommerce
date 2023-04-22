package com.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ResponseDto;
import com.ecommerce.dto.user.SignUpDto;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public ResponseDto addNewUser(SignUpDto signUpDto) {
		Optional<User> userByEmail = userRepo.findUserByEmail(signUpDto.getEmail());
        
		if(userByEmail.isPresent()) {
            User existingUser = userByEmail.get();
			throw new IllegalStateException("a user already exists with that email:" + existingUser);
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
        
        ResponseDto responseDto = new ResponseDto("succes", "demo response");
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

}