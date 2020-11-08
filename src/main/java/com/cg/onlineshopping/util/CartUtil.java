package com.cg.onlineshopping.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;
import com.cg.onlineshopping.entities.Cart;
import com.cg.onlineshopping.modal.CartDetails;

@Component
public class CartUtil {
	public List<CartDetails> toDetails(Collection<Cart> carts) {
		List<CartDetails> desired = new ArrayList<>();
		for (Cart cart : carts) {
			CartDetails details = toDetails(cart);
			desired.cart(details);
		}
		return desired;
	}
	public CartDetails toDetails(Cart cart) {
		return new CartDetails(cart.getCartId(),cart.getUserId(), cart.getProducts());
	}
}
