package com.ecommerce.ecommercebackend.common;

import java.time.LocalDateTime;

public class ApiResponse {
    private final boolean sucess;
    private final String message;

    public ApiResponse(boolean sucess, String message) {
        this.sucess = sucess;
        this.message = message;
    }

    public boolean isSucess() {
        return sucess;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp(){
        return LocalDateTime.now().toString();
    }
}
