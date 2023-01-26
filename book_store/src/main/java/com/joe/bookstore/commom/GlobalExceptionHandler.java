package com.joe.bookstore.commom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity	handelException(Exception e) {
		
		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiResponse.setError("Ooops Someting went wrong");
		ResponseEntity<APIResponse> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
		
		return response;
		
	}
	
	@ExceptionHandler
	public ResponseEntity	handelBadrequestException(BadRequestException e) {
		
		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		apiResponse.setError(e.getErrors());
		ResponseEntity<APIResponse> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
		
		return response;
		
	}
	
	@ExceptionHandler
	public ResponseEntity	handelAccessDeniedException(AccessDeniedException e) {
		
		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		apiResponse.setError(e.getMessage());
		ResponseEntity<APIResponse> response = ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		
		return response;
		
	}
	
	
}
