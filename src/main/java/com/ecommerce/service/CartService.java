package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@Service
public class CartService 
{
    @Autowired
    private CartRepository cartrepository;

    @Autowired
    private UserRepository userrepository;

    @Autowired
    private ProductRepository productrepository;


    // Add Product To Logged-In User Cart
    public Cart addToCart(Cart cart, String email)
    {
        User user = userrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Product product = productrepository.findById(cart.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        cart.setUser(user);
        cart.setProduct(product);

        return cartrepository.save(cart);
    }


    // Get Logged-In User Cart
    public List<Cart> getCartItemsByUser(String email)
    {
        User user = userrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return cartrepository.findByUser(user);
    }


    // Remove Only Logged-In User's Cart Item
    public String removeCartItem(Long id, String email)
    {
        User user = userrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Cart cart = cartrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart Item Not Found"));

        if(!cart.getUser().getId().equals(user.getId()))
        {
            throw new RuntimeException("You cannot remove this cart item");
        }

        cartrepository.delete(cart);

        return "Cart Item Removed";
    }


    // Clear Only Logged-In User Cart
    public void clearCart(String email)
    {
        User user = userrepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        cartrepository.deleteByUser(user);
    }
}