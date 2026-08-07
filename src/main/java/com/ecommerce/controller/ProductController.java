package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController 
{
	@Autowired
	private ProductService productservice;
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Autowired
	private CategoryRepository categoryrepository;

	@PostMapping
	public Product addProduct(
	        @RequestParam("productname") String productname,
	        @RequestParam("price") Double price,
	        @RequestParam("quantity") Integer quantity,
	        @RequestParam("description") String description,
	        @RequestParam("categoryId") Long categoryId,
	        @RequestParam("image") MultipartFile image) throws IOException {

		String fileName = image.getOriginalFilename();

		Path path = Paths.get(uploadDir, fileName);


		Files.createDirectories(path.getParent());

		Files.write(path, image.getBytes());


		Product product = new Product();

	    product.setProductname(productname);
	    product.setPrice(price);
	    product.setQuantity(quantity);
	    product.setDescription(description);
	    product.setImageName(fileName);
	    Category category= categoryrepository.findById(categoryId).orElse(null);
	    product.setCategory(category);

	    return productservice.addProduct(product);
	}
	
	@GetMapping
	public List<Product> getAllProducts()
	{
		return productservice.getAllProducts();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	    Product product = productservice.getProductById(id);
	    return ResponseEntity.ok(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(
	        @PathVariable Long id,
	        @RequestParam("productname") String productname,
	        @RequestParam("price") Double price,
	        @RequestParam("quantity") Integer quantity,
	        @RequestParam("description") String description,
	        @RequestParam("categoryId") Long categoryId,
	        @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

	    Product product = productservice.getProductById(id);

	    product.setProductname(productname);
	    product.setPrice(price);
	    product.setQuantity(quantity);
	    product.setDescription(description);

	    Category category = categoryrepository.findById(categoryId).orElse(null);
	    product.setCategory(category);

	    if (image != null && !image.isEmpty()) {

	        String fileName = image.getOriginalFilename();

	        Path path = Paths.get(uploadDir, fileName);

	        Files.createDirectories(path.getParent());

	        Files.write(path, image.getBytes());

	        product.setImageName(fileName);
	    }

	    return productservice.updateProduct(id, product);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id)
	{
		return productservice.deleteProduct(id);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword)
	{
		List<Product> products = productservice.searchProduct(keyword);
		return ResponseEntity.ok(products);
	}
	@GetMapping("/category/{id}")
	public ResponseEntity<List<Product>>getProductsByCategory(@PathVariable Long id)
	{
		List<Product> products = productservice.getProductByCategory(id);
		return ResponseEntity.ok(products);
	}

}
