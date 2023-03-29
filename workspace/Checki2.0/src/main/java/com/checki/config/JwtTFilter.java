package com.checki.config;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.checki.user.service.UserService;
import com.checki.utils.JwtTokenUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor 
@Slf4j 
public class JwtTFilter extends OncePerRequestFilter {
	
	private final UserService userservice; 
	private final String secretKey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorization=request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("authentication : {}",authorization);
		
		if(authorization==null || !authorization.startsWith("Bearer ")) {
			log.warn("authorization을 잘못 보냈습니다.");
			filterChain.doFilter(request, response);
			return;
		}
		
		//Token 꺼내기
		String token=authorization.split(" ")[1];
		
		//Token Expired 되었는지 여부
		
		if(JwtTokenUtil.isExpired(token, secretKey)) {
			log.warn("Token이 만료 되었습니다");
			filterChain.doFilter(request, response);
		}
		
		//UserName Token에서 꺼내기
		String userName=JwtTokenUtil.getUserName(token,secretKey);
	
		
		//권한부여
		UsernamePasswordAuthenticationToken authenticationToken=
				new UsernamePasswordAuthenticationToken(userName,null,List.of(new SimpleGrantedAuthority("A")));
		
		//Detail을  넣어줌
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
		
		
	}




}
