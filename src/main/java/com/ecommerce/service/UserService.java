package com.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.entity.User;
import com.ecommerce.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userrepository;
	
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public User registerUser(User user)
	{
		user.setRole("USER");
		return userrepository.save(user);
	}
	public String loginUser(String email,String password)
	{
		User user= userrepository.findByEmail(email).orElse(null);
		if(user == null)
		{
			return "User Not Found";
		}
		if(!user.getPassword().equals(password))
		{
			return "Invalid Password";
		}
		return "Login Success";
	}
	
	public User authenticateUser(String email,String password)
	{
		User user=userrepository.findByEmail(email).orElse(null);
		if(user == null)
		{
			return null;
		}
		if(! user.getPassword().equals(password))
		{
			return null;
		}
		return user;
	}
	public User getProfile(String email)
	{
		return userrepository.findByEmail(email).orElse(null);
	}
	
	public User updateProfile(String currentEmail, String name, String email) {

	    User user = userrepository.findByEmail(currentEmail).orElse(null);

	    if (user == null) {
	        return null;
	    }

	    user.setName(name);
	    user.setEmail(email);

	    return userrepository.save(user);
	}
	public boolean changePassword(String email, String currentPassword, String newPassword) {

	    User user = userrepository.findByEmail(email).orElse(null);

	    if (user == null) {
	        return false;
	    }

	    if (!user.getPassword().equals(currentPassword)) {
	        return false;
	    }

	    user.setPassword(newPassword);

	    userrepository.save(user);

	    return true;
	}
	
	public User uploadProfileImage(String email, MultipartFile file) throws IOException {

	    User user = userrepository.findByEmail(email).orElse(null);

	    if (user == null) {
	        return null;
	    }

	    File directory = new File(uploadDir);

	    if (!directory.exists()) {
	        directory.mkdirs();
	    }

	    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

	    File destination = new File(directory, fileName);

	    file.transferTo(destination);

	    user.setProfileImage(fileName);

	    return userrepository.save(user);
	}

}
