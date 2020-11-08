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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.entities.User;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;
import com.cg.onlineshopping.exception.UserAlreadyExistsException;
import com.cg.onlineshopping.exception.UserNotFoundException;
import com.cg.onlineshopping.modal.AddressDetails;
import com.cg.onlineshopping.modal.CartDetails;
import com.cg.onlineshopping.modal.CreateAddressRequest;
import com.cg.onlineshopping.modal.CreateUserRequest;
import com.cg.onlineshopping.modal.UpdateAddressRequest;
import com.cg.onlineshopping.modal.UpdateUserRequest;
import com.cg.onlineshopping.modal.UserDetails;
import com.cg.onlineshopping.service.IAddressService;
import com.cg.onlineshopping.service.ICartService;
import com.cg.onlineshopping.service.ILoginService;
import com.cg.onlineshopping.util.AddressUtil;
import com.cg.onlineshopping.util.UserUtil;

@Validated
@RequestMapping("/user")
@RestController
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private ILoginService  service;
	@Autowired
	private UserUtil userUtil;
	
	@PostMapping("/add")
	public ResponseEntity<UserDetails> addUser(@RequestBody @Valid CreateUserRequest requestData)
			throws UserAlreadyExistsException {
		try {
			User user = new User(requestData.getUserId(), requestData.getPassword(), requestData.getRole());
			user = service.addUser(user);
			UserDetails details = userUtil.toDetails(user);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to add user:{} errorlog: ", requestData.getUserId(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	

	@DeleteMapping("/remove/{userId}")
	public ResponseEntity<User> removeUser(@PathVariable("userId") User user)
			throws UserNotFoundException {
		try {
			service.removeUser(user.getUserId());
			return new ResponseEntity<User>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
			LOGGER.error("unable to delete user for userId:{} errlog", user.getUserId(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}
	@PutMapping("{id}")
	public ResponseEntity<User> validateUser(@PathVariable("userId") User user)
	throws UserNotFoundException {
		try {
			service.validateUser(user);
			return new ResponseEntity<User>(HttpStatus.OK);
		} catch(UserNotFoundException e) {
			LOGGER.error("unable to validate user for userId:{} errlog", user.getUserId(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
	
	
}
