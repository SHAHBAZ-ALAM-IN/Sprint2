package com.cg.onlineshopping.exception;

public class UserNotFoundException extends Exception {
	/** user not found exception
	 */
	private static final long serialVersionUID = 1L;
	public UserNotFoundException() {}
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
