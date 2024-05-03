package com.ecommerce.ecommercebackend.exceptions;

public class AuthentificationFailException extends IllegalArgumentException{
    public AuthentificationFailException(String msg){
        super(msg);
    }
}
