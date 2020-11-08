package com.cg.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.onlineshopping.entities.Address;

public interface IAddressRepository extends JpaRepository<Address, Integer> {

}