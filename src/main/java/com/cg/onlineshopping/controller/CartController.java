package com.cg.onlineshopping.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.entities.Cart;
import com.cg.onlineshopping.entities.User;
import com.cg.onlineshopping.exception.AddressNotFoundException;
import com.cg.onlineshopping.exception.CartAlreadyExistsException;
import com.cg.onlineshopping.exception.CartNotFoundException;
import com.cg.onlineshopping.exception.UserAlreadyExistsException;
import com.cg.onlineshopping.exception.UserNotFoundException;
import com.cg.onlineshopping.modal.AddressDetails;
import com.cg.onlineshopping.modal.CartDetails;
import com.cg.onlineshopping.modal.CreateCartRequest;
import com.cg.onlineshopping.modal.CreateUserRequest;
import com.cg.onlineshopping.modal.UpdateAddressRequest;
import com.cg.onlineshopping.modal.UpdateCartRequest;
import com.cg.onlineshopping.modal.UserDetails;
import com.cg.onlineshopping.service.ICartService;
import com.cg.onlineshopping.service.ILoginService;
import com.cg.onlineshopping.service.Product;
import com.cg.onlineshopping.util.CartUtil;

@Validated
@RequestMapping("/user")
@RestController
public class CartController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private ICartService  service;
	@Autowired
	private CartUtil cartUtil;
	
	@PostMapping("/add")
		public ResponseEntity<CartDetails> addProductToCart(@RequestBody @Valid CreateCartRequest requestData)
				throws CartAlreadyExistsException {
			try {
				Cart cart = new Cart(requestData.getCartId(), requestData.getUserId(), requestData.getProducts());
				cart = service.addProductToCart(cart,product, quantity);
				CartDetails details = cartUtil.toDetails(cart);
				return new ResponseEntity<>(details, HttpStatus.OK);
			} catch (Exception e) {
				LOGGER.error("unable to add cart:{} errorlog: ", requestData.getCartId(), e);
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
	
	@DeleteMapping("/remove/{cartId}")
	public ResponseEntity<Cart>  removeProductFromCart(@PathVariable("cartId") Cart cart,Product product)
			throws UserNotFoundException {
		try {
			service.removeProductFromCart(cart.getUserId(),product);
			return new ResponseEntity<Cart>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
			LOGGER.error("unable to delete cart for cartId:{} errlog", cart.getCartId(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
	
	@PutMapping("/update")
	public ResponseEntity<CartDetails> updateProductQuantity(@RequestBody @Valid UpdateCartRequest requestData)
			throws CartNotFoundException {
		try {
			Cart cart = new Cart(requestData.getCartId(), requestData.getUserId(), requestData.getProducts());
			cart.setCartId(requestData.getCartId());
			cart = service.updateProductQuantity(cart,product,quantity);
			CartDetails details = cartUtil.toDetails(cart);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to update cart for CartId:{} errlog: ", requestData.getCartId(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	@RequestMapping("/viewall")
	public ResponseEntity<List<Cart>>  viewAllProducts() {
		try {
			return new ResponseEntity<>(service. viewAllProducts(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to view all the products: ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}


