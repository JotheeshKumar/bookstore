package com.joe.bookstore.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.joe.bookstore.dto.RequestMeta;
import com.joe.bookstore.util.JwtUtils;

import io.jsonwebtoken.Claims;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private JwtUtils jwtUtils;

	private RequestMeta requestMeta;
	
	
	
	public JwtInterceptor(RequestMeta requestMeta) {
		this.requestMeta = requestMeta;
	}



	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		
		String auth = request.getHeader("authorization");
		//System.out.println("auth: "+auth);
		if(!(requestURI.contains("login")||requestURI.contains("signup"))) {
		
			Claims claims = jwtUtils.verify(auth);
			requestMeta.setUserName( claims.get("name").toString());
			requestMeta.setEmailId( claims.get("emailId").toString());
			requestMeta.setUserType( claims.get("type").toString());
			requestMeta.setUserId( Integer.valueOf( claims.getIssuer()));
			
		}
	
		
		
		
		System.out.println("-----PreHandle-----");
		return super.preHandle(request, response, handler);
	}
}
