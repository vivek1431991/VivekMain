package com.basic.auth.exception;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String string) {
	   super("User Not found. Please login with a valid credentials");
	}

}
