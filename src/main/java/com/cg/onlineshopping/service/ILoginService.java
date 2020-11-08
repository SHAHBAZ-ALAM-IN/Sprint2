package com.cg.onlineshopping.service;

import com.cg.onlineshopping.entities.User;
import com.cg.onlineshopping.exception.UserAlreadyExistsException;
import com.cg.onlineshopping.exception.UserNotFoundException;

public interface ILoginService {
	public User addUser(User user) throws UserAlreadyExistsException;
	public User removeUser(User user) throws UserNotFoundException ;
	public User validateUser(User user) throws UserNotFoundException;
	public User signOut(User user);
	public User findById(String userId) throws UserNotFoundException;
}
