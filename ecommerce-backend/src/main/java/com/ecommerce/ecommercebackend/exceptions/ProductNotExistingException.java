package com.ecommerce.ecommercebackend.exceptions;

public class ProductNotExistingException extends IllegalArgumentException {
    public ProductNotExistingException(String msg) {
        super(msg);
    }
}
