package com.cg.onlineshopping.modal;

import java.util.Map;

import com.cg.onlineshopping.entities.Product;

public class CartDetails {
	private String cartId;
    private String userId;
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
	
    public CartDetails(String cartId, String userId, Map<Product, Integer> products) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.products = products;
	}

}
