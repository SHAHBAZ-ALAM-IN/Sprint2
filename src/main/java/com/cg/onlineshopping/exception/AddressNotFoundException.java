package com.cg.onlineshopping.exception;

public class AddressNotFoundException extends Exception {
	/**
	 * address not found exception
	 */
	private static final long serialVersionUID = 1L;

	public AddressNotFoundException() {
	}

	public AddressNotFoundException(String msg) {
		super(msg);
	}
}