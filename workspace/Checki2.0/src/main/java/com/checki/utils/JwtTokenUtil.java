package com.checki.utils;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {

	
	public static boolean isExpired(String token,String secretKey) {
		
		
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration().before(new Date());
	}
	
	public static String getUserName(String token,String secretKey)
	{
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
				.getBody().get("userName",String.class);
	}
	
	public static String createToken(String username,String key,long expireTimeMs) {
		Claims claims=Jwts.claims();
		claims.put("userName",username);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date (System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+expireTimeMs))
				.signWith(SignatureAlgorithm.HS256, key)
				.compact()
				;
	}


	
}
