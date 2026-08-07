package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public Cart addToCart(@RequestBody Cart cart)
	{
		return cartservice.addToCart(cart);
	}
	
	@GetMapping
	public List<Cart> getCartItems()
	{
		return cartservice.getAllCartItems();
	}
	
	@DeleteMapping("/{id}")
	public String removeCartItem(@PathVariable Long id)
	{
		return cartservice.removeCartItem(id);
	}
	
	@DeleteMapping("/clear")
	public ResponseEntity<String> clearCart()
	{
		cartservice.clearCart();
		return ResponseEntity.ok("Cart Cleared");
	}
}
