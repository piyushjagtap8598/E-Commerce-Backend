package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductService 
{
	@Autowired
	private ProductRepository productrepository;
	
	public Product addProduct(Product product)
	{
		return  productrepository.save(product);
	}
	
	public List<Product> getAllProducts()
	{
		return productrepository.findAll();
	}
	
	
	public Product updateProduct(Long id,Product product)
	{
		Product existingProduct = productrepository.findById(id).orElse(null);
		if(existingProduct != null)
		{
			existingProduct.setProductname(product.getProductname());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setQuantity(product.getQuantity());
			existingProduct.setDescription(product.getDescription());
			
			return productrepository.save(existingProduct);
		}
		return null;
	}
	public String deleteProduct(Long id)
	{
		productrepository.deleteById(id);
		return "Product Deleted Succesfully";
	}
	
	public List<Product> searchProduct(String keyword)
	{
		return productrepository.findByProductnameContainingIgnoreCase(keyword);
	}
	public List<Product> getProductByCategory(Long categoryId)
	{
		return productrepository.findByCategoryId(categoryId);
	}
	public Product getProductById(Long id)
	{
	    return productrepository.findById(id).orElse(null);
	}

}
