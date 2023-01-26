package com.joe.bookstore.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.joe.bookstore.commom.AccessDeniedException;
import com.joe.bookstore.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static long expiryDuration=60*60;
	private static String secret="This_is_secret";
	public String generateJwt(User user) {
		
		long milliTime=System.currentTimeMillis();
		long expiryTime=milliTime+expiryDuration*1000;
		
		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);
		//claims
		Claims claims= Jwts.claims()
				.setIssuer(user.getId().toString())
				.setIssuedAt(issuedAt)
				.setExpiration(expiryAt);
		
		claims.put("type",user.getUserType());
		claims.put("name", user.getName());
		claims.put("emailId", user.getEmailId());
		
		return	Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		
		
		
	}
	
	public Claims verify(String	authorization) throws Exception {
		try {
			Claims claims = (Claims) Jwts.parser().setSigningKey(secret).parse(authorization).getBody();
			String name = (String) claims.get("name");
			System.out.println(name);
			return claims;
		} catch (Exception e) {
		throw new AccessDeniedException("Access denied");
		}
		
	}
	
	
}
