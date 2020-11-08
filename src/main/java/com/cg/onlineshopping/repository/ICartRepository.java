package com.cg.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlineshopping.entities.Cart;


public interface ICartRepository extends JpaRepository<Cart, String>{

}
