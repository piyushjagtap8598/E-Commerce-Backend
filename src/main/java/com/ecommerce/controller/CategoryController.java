package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Category;
import com.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController 
{
	@Autowired
	private CategoryService categoryservice;
	
	@PostMapping
	public Category addCategory(@RequestBody Category category)
	{
		return categoryservice.addCategory(category);
	}
	
	@GetMapping
	public List<Category> getAllCategories()
	{
		return categoryservice.getAllCategories();
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Long id,@RequestBody Category category)
	{
		return categoryservice.updateCategory(id, category);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable Long id)
	{
		return categoryservice.deleteCategory(id);
	}

}
