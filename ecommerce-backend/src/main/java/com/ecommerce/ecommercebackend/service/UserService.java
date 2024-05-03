package com.ecommerce.ecommercebackend.service;

import com.ecommerce.ecommercebackend.dto.ResponseDto;
import com.ecommerce.ecommercebackend.dto.SignInDto;
import com.ecommerce.ecommercebackend.dto.user.SignInResponseDto;
import com.ecommerce.ecommercebackend.dto.user.SignupDto;
import com.ecommerce.ecommercebackend.exceptions.AuthentificationFailException;
import com.ecommerce.ecommercebackend.exceptions.CustomException;
import com.ecommerce.ecommercebackend.model.AuthenticationToken;
import com.ecommerce.ecommercebackend.model.User;
import com.ecommerce.ecommercebackend.repository.UserRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthentificationService authentificationService;
    @Transactional
    public ResponseDto signUp(SignupDto signupDto){

        //check if user is already present

        if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))){

            throw new CustomException("user is already present");
        }

        //hash the password

        String encryptedPassword = signupDto.getPassword();
        try{
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //save the user
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedPassword);
        userRepository.save(user);

        //create the token
        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        //save the token
        authentificationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success", "user created sucessfully");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {

        User user = userRepository.findByEmail(signInDto.getEmail());

        if(Objects.isNull(user)){
            throw new AuthentificationFailException("user is not valid");
        }

        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthentificationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthenticationToken token = authentificationService.getToken(user);

        if(Objects.isNull(token)){
            throw new CustomException("token is not present");
        }
        return new SignInResponseDto("success", token.getToken());
    }
}
