package com.cg.onlineshopping.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.mapping.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.entities.Cart;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;
import com.cg.onlineshopping.exception.CartAlreadyExistsException;
import com.cg.onlineshopping.exception.CartNotFoundException;
import com.cg.onlineshopping.repository.IAddressRepository;
import com.cg.onlineshopping.repository.ICartRepository;

import com.cg.onlineshopping.service.ICartService;

@Transactional
@Service
public class CartServiceImpl implements ICartService{
	
	/**
     * This is the method which contains all the
     * implementation of interface class ICartService
     *
     * @param entityManager  object manages a set of entities that are defined by a persistence unit.
     * @param repo used to call the ICartRepository
     */
	@Autowired
    private ICartRepository repo;
	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

	
    /**
      */
	
    @Override
    public Cart addProductToCart(Cart cart, Product product, int quantity) throws CartAlreadyExistsException {
    	LOGGER.info("adding product with cartId:{}", cart.getCartId());
		boolean exists = cart.getCartId() != null && repo.existsById(cart.getCartId()) && cart.getProducts() != null && repo.existsById(cart.getProducts());
		if (exists) {
			throw new AddressAlreadyExistsException(
					String.format("cart and product already exists for cartId id= %d", cart.getCartId())," product id= %d", product.getProductId());
		}
		cart = repo.save(product,quantity);
		return cart;

	}
		 

    }

    /**
     * This method is used to remove the product from cart.
     * we can remove products of cart via this method
     *
     * @return carts used to return the cart.
     */
    @Override
    public Cart removeProductFromCart(Cart cart, Product product) throws CartNotFoundException {
    	LOGGER.info("removing Cart from cartid : {}", cart.getCartId(),"removing product : {}",cart.getProducts());
		Cart carts=viewCart(cart,product);
    	repo.deleteById(cart.getCartId(),cart.getProducts());

    }

    /**
     * This method is used to update to cart.
     * we can update cart via this method
     *
     * @return carts used to return the updated cart.
     */
    @Override
    public Cart updateProductQuantity(Cart cart, Product product, int quantity) {
    	LOGGER.info("updating product quantity  with cartId: {}", cart.getCartId());
    	boolean exists=cart.getCartId()!=null && repo.existsById(cart.getCartId(), cart.getProducts());
		if(!exists) {
			throw new CartNotFoundException("Can't find, cart not found for id="+cart.getCartId());
		}
		return repo.save(cart,product,quantity);
		
	
    }

    /**
     * This method is used to remove all products from the cart.
     * we can remove all products via this method
     *
     * @return carts used to remove all the products in cart.
     */
    @Override
    public Cart removeAllProducts(Cart cart) {
        
    	Cart carts=viewCart(cart);
       return null;

    }

    /**
     * This method is used to commit changes to cart.
     * we can view all the products in cart via this method
     *
     * @return list is used to print all the product in cart.
     */
    @Override
    public List<Product> viewAllProducts(Cart cart) {
		LOGGER.info("retreving all the products from cart");
	
				/* Map<Product, Integer> productsMap = cart.getProducts();
			     Set<Product> products = productsMap.keySet();
			     List<Product> list = new ArrayList<>(products);
			     return list;*/
		List<Product> list = new ArrayList<Product>();
        repo.findAll().forEach(list::add);
        return list;

    }
    
    public Cart viewCart(String cartId) throws CartNotFoundException {
		LOGGER.info("viewing cart with cartId:{}", cartId);
		Optional<Cart> optional = repo.findById(cartId);
		if (!optional.isPresent())
			throw new CartNotFoundException("Can't find, address not found for id=" + cartId);
		return optional.get();
	}
    
}
