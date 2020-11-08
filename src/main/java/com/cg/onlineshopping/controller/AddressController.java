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
import com.cg.onlineshopping.exception.AddressAlreadyExistsException;
import com.cg.onlineshopping.exception.AddressNotFoundException;
import com.cg.onlineshopping.modal.AddressDetails;
import com.cg.onlineshopping.modal.CreateAddressRequest;
import com.cg.onlineshopping.modal.UpdateAddressRequest;
import com.cg.onlineshopping.service.IAddressService;
import com.cg.onlineshopping.util.AddressUtil;

@Validated
@RequestMapping("/address")
@RestController
public class AddressController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private IAddressService service;
	@Autowired
	private AddressUtil addressUtil;

	@PostMapping("/add")
	public ResponseEntity<AddressDetails> addAddress(@RequestBody @Valid CreateAddressRequest requestData)
			throws AddressAlreadyExistsException {
		try {
			Address add = new Address(requestData.getStreetNo(), requestData.getBuildingName(), requestData.getCity(),
					requestData.getState(), requestData.getCountry(), requestData.getPincode());
			add = service.addAddress(add);
			AddressDetails details = addressUtil.toDetails(add);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to add address:{} errorlog: ", requestData.getBuildingName(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/update")
	public ResponseEntity<AddressDetails> update(@RequestBody @Valid UpdateAddressRequest requestData)
			throws AddressNotFoundException {
		try {
			Address add = new Address(requestData.getStreetNo(), requestData.getBuildingName(), requestData.getCity(),
					requestData.getState(), requestData.getCountry(), requestData.getPincode());
			add.setAddressId(requestData.getAddressId());
			add = service.updateAddress(add);
			AddressDetails details = addressUtil.toDetails(add);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to update address for addressId:{} errlog: ", requestData.getAddressId(), e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/get/id/{id}")
	public ResponseEntity<AddressDetails> viewAddress(@PathVariable("id") int addressId)
			throws AddressNotFoundException {
		try {
			Address add = service.viewAddress(addressId);
			AddressDetails details = addressUtil.toDetails(add);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to view address for addressId:{} errlog", addressId, e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping("/viewall")
	public ResponseEntity<List<Address>> viewAllAddress() {
		try {
			return new ResponseEntity<>(service.viewAllAddress(), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("unable to view all the address: ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/remove/{addressId}")
	public ResponseEntity<Void> removeAddress(@PathVariable("addressId") Integer addressId)
			throws AddressNotFoundException {
		try {
			service.removeAddress(addressId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (AddressNotFoundException e) {
			LOGGER.error("unable to delete address for addressId:{} errlog", addressId, e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
}
