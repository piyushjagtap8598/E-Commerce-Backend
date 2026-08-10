package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Order;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.entity.OrderItem;

@Service
public class OrderService 
{
	@Autowired
	private OrderRepository orderrepository;
	
	@Autowired
	private ProductRepository productrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	public Order placeOrder(Order order, String email)
	{
		User user=userrepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User Is Not Found"));
		order.setUser(user);
		order.setStatus("Placed");
		if(order.getOrderitems() != null)
		{
			order.getOrderitems().forEach(item ->{item.setOrder(order);});
		}
		return orderrepository.save(order);
	}
	
	public List<Order> getOrderByUser(String email)
	{
		User user=userrepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));
		return orderrepository.findByUser(user);
	}
	
	public Order getOrderById(Long id)
	{
		return orderrepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	}
	public Order buyNow(Order order, String email)
	{
		User user=userrepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User Not Found"));
		order.setUser(user);
	    order.setStatus("Placed");

	    if(order.getOrderitems() != null)
	    {
	        for(OrderItem item : order.getOrderitems())
	        {
	            Product product = productrepository
	                    .findById(item.getProduct().getId())
	                    .orElseThrow(() -> new RuntimeException("Product Not Found"));

	            if(product.getQuantity() < item.getQuantity())
	            {
	                throw new RuntimeException(product.getProductname() + " is Out Of Stock");
	            }

	            // Stock Update
	            product.setQuantity(product.getQuantity() - item.getQuantity());

	            productrepository.save(product);

	            item.setPrice(product.getPrice());
	            item.setOrder(order);
	        }
	    }

	    return orderrepository.save(order);
	}

}
