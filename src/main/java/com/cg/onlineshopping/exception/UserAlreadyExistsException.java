package com.cg.onlineshopping.exception;

public class UserAlreadyExistsException extends Exception {
	/**
	 * user not found exception
	 */
	private static final long serialVersionUID = 1L;
	public UserAlreadyExistsException(){}
	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
}
