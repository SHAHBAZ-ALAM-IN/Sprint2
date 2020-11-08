package com.cg.onlineshopping.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;
import com.cg.onlineshopping.repository.IAddressRepository;
import com.cg.onlineshopping.service.IAddressService;

@Transactional
@Service
public class AddressServiceImpl implements IAddressService {
	@Autowired
	private IAddressRepository repo;
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Override
	public Address addAddress(Address add) throws AddressAlreadyExistsException {
		LOGGER.info("adding address for user with addressId:{}", add.getAddressId());
		boolean exists = add.getAddressId() != 0 && repo.existsById(add.getAddressId());
		if (exists) {
			throw new AddressAlreadyExistsException(
					String.format("address already exists for id= %d", add.getAddressId()));
		}
		add = repo.save(add);
		return add;

	}

	public Address viewAddress(int addressId) throws AddressNotFoundException {
		LOGGER.info("viewing address with addressId:{}", addressId);
		Optional<Address> optional = repo.findById(addressId);
		if (!optional.isPresent())
			throw new AddressNotFoundException("Can't find, address not found for id=" + addressId);
		return optional.get();
	}

	@Override
	public Address updateAddress(Address add) throws AddressNotFoundException {
		LOGGER.info("updating address with addressId: {}", add.getAddressId());
		boolean exists = add.getAddressId() != 0 && repo.existsById(add.getAddressId());
		if (!exists) {
			throw new AddressNotFoundException("Can't find, address not found for id=" + add.getAddressId());
		}
		return repo.save(add);
	}

	@Override
	public void removeAddress(int addressId) throws AddressNotFoundException {
		LOGGER.info("removing Flight with flightno : {}", addressId);
		repo.deleteById(addressId);

	}

	@Override
	public List<Address> viewAllAddress() {
		LOGGER.info("retreving all the address");
		List<Address> addresses = new ArrayList<>();
		repo.findAll().forEach(addresses::add);
		return addresses;
	}

}
