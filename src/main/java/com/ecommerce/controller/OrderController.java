package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ecommerce.entity.Order;
import com.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController 
{
	@Autowired
	private OrderService orderservice;
	
	@PostMapping
	public Order placeOrder(@RequestBody Order order, Authentication authentication)
	{
		String email= authentication.getName();
		System.out.println("Order Items=" +order.getOrderitems());
		return orderservice.placeOrder(order,email);
	}
	@PostMapping("/buy-now")
	public Order buyNow(@RequestBody Order order, Authentication authentication)
	{
		String email= authentication.getName();
	    return orderservice.buyNow(order,email);
	}
	
	@GetMapping
	public List<Order> getAllOrder(Authentication authentication)
	{
		String email = authentication.getName();
		return orderservice.getOrderByUser(email);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById (@PathVariable Long id)
	{
		Order order = orderservice.getOrderById(id);
		return ResponseEntity.ok(order);
		
	}

}
