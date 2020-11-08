package com.cg.onlineshopping.exception;

public class CartNotFoundException extends Exception {
	/**
	 * cart not found exception
	 */
	private static final long serialVersionUID = 1L;
	public CartNotFoundException() {}
	public CartNotFoundException(String msg) {
		super(msg);
	}
}