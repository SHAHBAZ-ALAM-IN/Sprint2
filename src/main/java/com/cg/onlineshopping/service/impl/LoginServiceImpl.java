package com.cg.onlineshopping.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineshopping.entities.User;
import com.cg.onlineshopping.exception.UserAlreadyExistsException;
import com.cg.onlineshopping.exception.UserNotFoundException;
import com.cg.onlineshopping.repository.ILoginRepository;
import com.cg.onlineshopping.service.ILoginService;

/**
 * The LoginServiceImpl program contains all the methods and these methods
 * call all the methods from  ILoginService
 * ILoginService interface class
 *
 * @author shahbaz alam
 * @version 1.0
 * @since 2020-10-19
 */
@Transactional
@Service
public class LoginServiceImpl implements ILoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

    /**
     * This is the method which contains all the
     * implementation of interface class ILoginService
     *
     * @param entityManager  object manages a set of entities that are defined by a persistence unit.
     * @param repo used to call the ILoginRepository
     */
	@Autowired
    private ILoginRepository repo;
    

    /**
     * This method is used to commit changes user.
     * we can add user via this method
     *
     * @return user used to return the user.
     */
    @Override
    public User addUser(User user) throws UserAlreadyExistsException  {
    	boolean exists=user.getUserId()!=null && repo.existsById(user.getUserId());
		if(exists) {
			throw new UserAlreadyExistsException("user already exists for id=" + user.getUserId());
		}
		user=repo.save(user);
		return user;

    }
   /* public User findById(int userId) throws UserNotFoundException {
		Optional<User> optional=repo.findById(userId);
		if(!optional.isPresent())
			throw new UserNotFoundException("Can't find, user not found for id="+ userId);
	return optional.get();
	}
    
	public void deleteById(int userId) throwsUserNotFoundException {
		User user=findById(userId);
		repo.delete(user);
	} */

    /**
     * This method is used to remove the user.
     * we can remove user via this method
     *
     * @return user used to return the user.
     */

    @Override
    public User removeUser(User user) throws UserNotFoundException {
    	 LOGGER.info("removing user with userId : {}", user.getUserId());
         User users=viewUser(user.getUserId());
         repo.deleteById(user.getUserId());
         
        return users;


    }

    /**
     * This method is validate user.
     * we can validate user via this method
     *
     * @return user used to return validated user.
     * @throws UserNotFoundException 
     */

    @Override
    public User validateUser(User user) throws UserNotFoundException {
    	
        User stored=findById(user.getUserId());
		if(stored.getPassword().equals(user.getPassword())) 
		{
			return user;
		}
		
		return null;
        
    }


    /**
     * This method is used to signOut user.
     * we can singOut user via this method
     *
     * @return user used to return signedOut user.
     */
    @Override
    public User signOut(User user) {
        return null;
    }
    public User viewUser(String userId) throws UserNotFoundException {
        LOGGER.info("viewing order with userId:{}", userId);
        Optional<User> optional = repo.findById(userId);
        if (!optional.isPresent())
            throw new UserNotFoundException("Can't find, user not found for id=" + userId);
        return optional.get();
    }

    @Override
    public User findById(String userId) throws UserNotFoundException {
        Optional<User> optional = repo.findById(userId);
        if (!optional.isPresent())
            throw new UserNotFoundException("Can't find,user not found for id=" + userId);
        return optional.get();
    }
    
    public void deleteById(String userId) throws UserNotFoundException {
        User user = findById(userId);
        repo.delete(user);
    }


}
