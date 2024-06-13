package com.ecommerce.ecommercebackend.service;

import com.ecommerce.ecommercebackend.exceptions.AuthentificationFailException;
import com.ecommerce.ecommercebackend.model.AuthenticationToken;
import com.ecommerce.ecommercebackend.model.User;
import com.ecommerce.ecommercebackend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthentificationService {
    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token){
        AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthentificationFailException{
        if(Objects.isNull(token)){
            throw new AuthentificationFailException("token doesn't exist");
        }
        if (Objects.isNull(getUser(token))){
            throw new AuthentificationFailException("Token is not valid");
        }
    }
}
