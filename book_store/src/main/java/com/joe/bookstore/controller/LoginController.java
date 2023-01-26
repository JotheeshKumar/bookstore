package com.joe.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joe.bookstore.commom.APIResponse;
import com.joe.bookstore.dto.LoginRequestDto;
import com.joe.bookstore.dto.SignUpRequestDto;
import com.joe.bookstore.service.LoginService;
import com.joe.bookstore.util.JwtUtils;


@Controller
@RequestMapping("/users")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	@PostMapping("/signup")
	public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
		
		APIResponse apiResponse = loginService.signUp(signUpRequestDto);
		return ResponseEntity.status(apiResponse.getStatus())
				.body(apiResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
		
		APIResponse apiResponse = loginService.login(loginRequestDto);
		return ResponseEntity.status(apiResponse.getStatus())
				.body(apiResponse);
	}
	@GetMapping("/private")
	public ResponseEntity<APIResponse> privateApi(@RequestHeader (value = "authorization", defaultValue = "") String auth) throws Exception {
		
		//String auth1="";
		//String auth="eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiIyIiwiaWF0IjoxNjY4MzM4MTY2LCJleHAiOjE2NjgzNDE3NjYsInR5cGUiOiJOT1JNQUwiLCJuYW1lIjoidXNlcjIiLCJlbWFpbElkIjoidXNlcjJAbWFpbC5jb20ifQ.YGpNezqhyhuAB1XGqGYmSz2mgiKi_zNv1u9Yeig4NH7e8RzmezVtahyp3Y0FSSgf1S8z152zjW2tRcqZlNLDPg";
		jwtUtils.verify(auth);
		
		APIResponse apiResponse = new APIResponse();
		apiResponse.setData("this is private");
		return ResponseEntity.status(apiResponse.getStatus())
				.body(apiResponse);
	}
}
