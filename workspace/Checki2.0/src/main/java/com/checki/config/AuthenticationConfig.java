package com.checki.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.checki.user.service.UserService;


import lombok.RequiredArgsConstructor;

@Configuration 
@EnableWebSecurity 
@RequiredArgsConstructor 
public class AuthenticationConfig {

	private final UserService userService;
	
	@Value("${jwt.secret}")
	private String secretKey;
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity  httpSecurity)throws Exception{
		
		return httpSecurity
				.httpBasic().disable()
				.csrf().disable()
				.cors().and()
				 .authorizeHttpRequests(request -> request
				               .requestMatchers("/user/Login").permitAll()
		                       .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
		                     
		                )
				 .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .addFilterBefore(new JwtTFilter(userService,secretKey), UsernamePasswordAuthenticationFilter.class)
	             .getOrBuild();
			
              
	}
}
