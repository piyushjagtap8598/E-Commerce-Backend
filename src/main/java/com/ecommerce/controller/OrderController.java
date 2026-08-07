package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public Order placeOrder(@RequestBody Order order)
	{
		System.out.println("Order Items=" +order.getOrderitems());
		return orderservice.placeOrder(order);
	}
	@PostMapping("/buy-now")
	public Order buyNow(@RequestBody Order order)
	{
	    return orderservice.buyNow(order);
	}
	
	@GetMapping
	public List<Order> getAllOrder()
	{
		return orderservice.getAllOrders();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById (@PathVariable Long id)
	{
		Order order = orderservice.getOrderById(id);
		return ResponseEntity.ok(order);
		
	}

}
