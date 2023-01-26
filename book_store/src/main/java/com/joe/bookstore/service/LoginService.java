package com.joe.bookstore.service;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joe.bookstore.commom.APIResponse;
import com.joe.bookstore.dto.LoginRequestDto;
import com.joe.bookstore.dto.SignUpRequestDto;
import com.joe.bookstore.entity.User;
import com.joe.bookstore.repo.UserRepository;
import com.joe.bookstore.util.JwtUtils;


@Service
public class LoginService {
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	JwtUtils jwtUtils;

	public APIResponse signUp(SignUpRequestDto signUpRequestDto) {
		APIResponse apiResponse = new APIResponse();
		
		
		//setup entity from dto
		User userEntity= new User();
		userEntity.setEmailId(signUpRequestDto.getEmailId());
		userEntity.setGender(	signUpRequestDto.getGender());
		userEntity.setName( signUpRequestDto.getName());
		userEntity.setPassword( signUpRequestDto.getPassword());
		userEntity.setIsActive(Boolean.TRUE);
		userEntity.setPhoneNumber(signUpRequestDto.getPhoneNumber());
		
		
		//save user
		userEntity= userRepository.save(userEntity);
		
		//return
		apiResponse.setData(userEntity);
		String token = jwtUtils.generateJwt(userEntity);
		Map<String, Object> data= new HashMap<>();
		data.put("accestoken", token);
		apiResponse.setData(data);
	
		
		return apiResponse;
	}

	public APIResponse login(LoginRequestDto loginRequestDto) {
		APIResponse apiResponse = new APIResponse();
		
		User user=userRepository.findOneByEmailIdIgnoreCaseAndPassword(loginRequestDto.getEmailId(), loginRequestDto.getPassword());
		
		if(user==null) {
			apiResponse.setData("User not available");
			return apiResponse;
		}
		
		String token = jwtUtils.generateJwt(user);
		Map<String, Object> data= new HashMap<>();
		data.put("accestoken", token);
		apiResponse.setData(data);
		
		return apiResponse;
	}

	
}
