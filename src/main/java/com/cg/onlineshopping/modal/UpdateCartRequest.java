package com.cg.onlineshopping.modal;

import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateCartRequest {
	@NotBlank
	@Size(min = 2, max = 20)
	private String cartId;
	@NotBlank
	@Size(min = 2, max = 20)
    private String userId;
	@NotBlank
	@Size(min = 2, max = 20)
    private Map<Product, Integer> products;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Map<Product, Integer> getProducts() {
		return products;
	}
	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

}
