package com.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String productname;
	private Double price;
	private Integer quantity;
	private String description;
	private String imageName;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	public Product() {
		
		
	}
	public Product(Long id, String productname, Double price, Integer quantity, String description,String imageName)
	{
		
		this.id = id;
		this.productname = productname;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.imageName=imageName;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getImageName() {
	    return imageName;
	}

	public void setImageName(String imageName) {
	    this.imageName = imageName;
	}
	
	
   
	

}
