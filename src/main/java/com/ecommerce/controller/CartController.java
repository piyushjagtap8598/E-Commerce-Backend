package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Cart;
import com.ecommerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController 
{
    @Autowired
    private CartService cartservice;

    @PostMapping
    public Cart addToCart(
            @RequestBody Cart cart,
            Authentication authentication)
    {
        String email = authentication.getName();

        return cartservice.addToCart(cart, email);
    }

    @GetMapping
    public List<Cart> getCartItems(
            Authentication authentication)
    {
        String email = authentication.getName();

        return cartservice.getCartItemsByUser(email);
    }

    @DeleteMapping("/{id}")
    public String removeCartItem(
            @PathVariable Long id,
            Authentication authentication)
    {
        String email = authentication.getName();

        return cartservice.removeCartItem(id, email);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(
            Authentication authentication)
    {
        String email = authentication.getName();

        cartservice.clearCart(email);

        return ResponseEntity.ok("Cart Cleared");
    }
}