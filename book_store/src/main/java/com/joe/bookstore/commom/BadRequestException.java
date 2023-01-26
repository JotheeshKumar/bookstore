package com.joe.bookstore.commom;

import java.util.ArrayList;
import java.util.List;

public class BadRequestException extends RuntimeException{

	List<Error> errors = new ArrayList<>();
	
	

	public BadRequestException(String msg,List<Error> errors) {
		super(msg);
		this.errors = errors;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	
	
}
