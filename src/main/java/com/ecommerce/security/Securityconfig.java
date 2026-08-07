package com.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class Securityconfig 
{
	@Autowired
	private JwtFilter jwtfilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.cors(cors -> {}).csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll().requestMatchers("/api/auth/**").permitAll().requestMatchers("/uploads/**").permitAll().requestMatchers("/api/profile/**").permitAll().requestMatchers("/api/cart/**").hasAnyRole("USER","ADMIN").requestMatchers("/api/orders/**").hasAnyRole("USER","ADMIN").requestMatchers(HttpMethod.GET,"/api/products/**").hasAnyRole("ADMIN","USER").requestMatchers(HttpMethod.POST,"/api/products/**").hasRole("ADMIN").requestMatchers(HttpMethod.PUT,"/api/products/**").hasRole("ADMIN").requestMatchers(HttpMethod.DELETE,"/api/products/**").hasRole("ADMIN").anyRequest().authenticated()).addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
	}

}
