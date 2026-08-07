package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.OrderItem;
import com.ecommerce.service.OrderItemService;

@RestController
@RequestMapping("/api/order-items")
@CrossOrigin("*")
public class OrderItemController 
{
	@Autowired
	private OrderItemService orderitemservice;
	
	@PostMapping
	public OrderItem addOrderItem(@RequestBody OrderItem orderitem)
	{
		return orderitemservice.addOrderItem(orderitem);
	}
	
	@GetMapping
	public List<OrderItem> getAllOrderItems()
	{
		return orderitemservice.getAllOrderItems();
	}
	

}
