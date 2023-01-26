package com.joe.bookstore.commom;

public class Error {

	private String target;
	private String message;
	
	public Error(String target, String message) {
		super();
		this.target = target;
		this.message = message;
	}
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Error [target=" + target + ", message=" + message + "]";
	}
	
	
	
	
}
