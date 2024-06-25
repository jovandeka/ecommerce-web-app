package com.ecommerce.ecommercebackend.controller;

import com.ecommerce.ecommercebackend.common.ApiResponse;
import com.ecommerce.ecommercebackend.dto.cart.AddToCartDto;
import com.ecommerce.ecommercebackend.dto.cart.CartDto;
import com.ecommerce.ecommercebackend.dto.cart.CartItemDto;
import com.ecommerce.ecommercebackend.model.Product;
import com.ecommerce.ecommercebackend.model.User;
import com.ecommerce.ecommercebackend.service.AuthentificationService;
import com.ecommerce.ecommercebackend.service.CartService;
import com.ecommerce.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthentificationService authentificationService;


    //add item
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token){
        authentificationService.authenticate(token);
        User user = authentificationService.getUser(token);

        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }
    //get all items
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
        authentificationService.authenticate(token);
        User user = authentificationService.getUser(token);

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
    //delete item
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId, @RequestParam("token") String token){
        authentificationService.authenticate(token);
        User user = authentificationService.getUser(token);

        cartService.deleteCartItem(itemId, user);
        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
