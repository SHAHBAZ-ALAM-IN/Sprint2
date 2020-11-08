package com.cg.onlineshopping.exception;

public class CartAlreadyExistsException extends Exception {
	/**
	 * cart already exists exception
	 */
	private static final long serialVersionUID = 1L;
public CartAlreadyExistsException(){}
	public CartAlreadyExistsException(String msg) {
		super(msg);
	}
}