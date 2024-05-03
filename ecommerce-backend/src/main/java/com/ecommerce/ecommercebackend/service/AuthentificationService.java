package com.ecommerce.ecommercebackend.service;

import com.ecommerce.ecommercebackend.model.AuthenticationToken;
import com.ecommerce.ecommercebackend.model.User;
import com.ecommerce.ecommercebackend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
