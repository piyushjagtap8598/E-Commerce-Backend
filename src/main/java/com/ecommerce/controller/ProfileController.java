package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dto.UpdateProfileRequest;
import com.ecommerce.entity.User;
import com.ecommerce.security.JwtUtil;
import com.ecommerce.service.UserService;
import com.ecommerce.dto.ChangePasswordRequest;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<?> getProfile(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // Remove "Bearer "
        String email = jwtUtil.extractUsername(token);

        User user = userService.getProfile(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("User Not Found");
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody UpdateProfileRequest request) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        User user = userService.updateProfile(
                email,
                request.getName(),
                request.getEmail());

        if (user == null) {
            return ResponseEntity.badRequest().body("User Not Found");
        }

        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ChangePasswordRequest request) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        boolean changed = userService.changePassword(
                email,
                request.getCurrentPassword(),
                request.getNewPassword()
        );

        if (!changed) {
            return ResponseEntity
                    .badRequest()
                    .body("Current Password Is Incorrect");
        }

        return ResponseEntity.ok("Password Changed Successfully");
    }
    
    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadProfileImage(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam("image") MultipartFile image) {

        try {

            String token = authHeader.substring(7);
            String email = jwtUtil.extractUsername(token);

            User user = userService.uploadProfileImage(email, image);

            if (user == null) {
                return ResponseEntity.badRequest().body("User Not Found");
            }

            return ResponseEntity.ok(user);

        } catch (Exception e) {
        	e.printStackTrace();

            return ResponseEntity.internalServerError()
                    .body(e.getMessage());

        }
    }

}