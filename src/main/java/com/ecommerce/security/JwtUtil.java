package com.ecommerce.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.*;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil 
{
	private static final String SECRET="mysecretkeymysecretkeymysecretkey123456";
	
	private static final Key KEY =Keys.hmacShaKeyFor(SECRET.getBytes());
	
	public String generateToken(String email)
	{
		return Jwts.builder().subject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+86400000)).signWith(KEY).compact();
	}
	public String extractUsername(String token)
	{
		return Jwts.parser().verifyWith((javax.crypto.SecretKey) KEY).build().parseSignedClaims(token).getPayload().getSubject();
	}
	public boolean validateToken(String token, String email)
	{
		return extractUsername(token).equals(email);
	}

}
