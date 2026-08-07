package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;

@Service
public class OrderItemService 
{
	@Autowired
	private OrderItemRepository orderitemrepository;
	
	@Autowired
	private OrderRepository orderrepository;
	
	@Autowired
	private ProductRepository productrepository;
	
	public OrderItem addOrderItem(OrderItem orderitem)
	{
		Order order= orderrepository.findById(orderitem.getOrder().getId()).orElse(null);
		Product prodduct = productrepository.findById(orderitem.getProduct().getId()).orElse(null);
		
		orderitem.setOrder(order);
		orderitem.setProduct(prodduct);
				
		return orderitemrepository.save(orderitem);
	}
	
	public List<OrderItem> getAllOrderItems()
	{
		return orderitemrepository.findAll();
	}
	

}
