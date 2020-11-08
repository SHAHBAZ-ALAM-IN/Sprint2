package com.cg.onlineshopping.exception;

public class AddressAlreadyExistsException extends Exception {
	/**
	 * address not found exception
	 */
	private static final long serialVersionUID = 1L;

	public AddressAlreadyExistsException() {
	}

	public AddressAlreadyExistsException(String msg) {
		super(msg);
	}
}