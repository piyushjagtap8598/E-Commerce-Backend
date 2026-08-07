package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@Service
public class CartService 
{
	@Autowired
	private CartRepository cartrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ProductRepository productrepository;
	
	
	public Cart addToCart( Cart cart)
	{
		User user= userrepository.findById(cart.getUser().getId()).orElse(null);
		Product product= productrepository.findById(cart.getProduct().getId()).orElse(null);
		
		cart.setUser(user);
		cart.setProduct(product);
		
		return cartrepository.save(cart);
	}
	public List<Cart> getAllCartItems()
	{
		return cartrepository.findAll();
	}
	
	public String removeCartItem(Long id)
	{
		cartrepository.deleteById(id);
		return "Cart Item Removed";
	}
	public void clearCart()
	{
		cartrepository.deleteAll();
	}

}
