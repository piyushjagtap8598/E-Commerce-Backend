package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService 
{
	@Autowired
	private CategoryRepository categoryrepository;
	
	public Category addCategory(Category category)
	{
		return categoryrepository.save(category);
	}
	
	public List<Category> getAllCategories()
	{
		return categoryrepository.findAll();
	}
	
	public Category updateCategory(Long id,Category category)
	{
		Category existingCategory = categoryrepository.findById(id).orElse(null);
		if(existingCategory != null)
		{
			existingCategory.setCategoryname(category.getCategoryname());
			return categoryrepository.save(existingCategory);
		}
		return null;
	}
	
	public String deleteCategory(Long id)
	{
		categoryrepository.deleteById(id);
		return "Category Deleted Succesfully";
	}

}
