package com.cg.onlineshopping.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cg.onlineshopping.entities.Address;
import com.cg.onlineshopping.modal.AddressDetails;

@Component
public class AddressUtil {
	public List<AddressDetails> toDetails(Collection<Address> address) {
		List<AddressDetails> desired = new ArrayList<>();
		for (Address add : address) {
			AddressDetails details = toDetails(add);
			desired.add(details);
		}
		return desired;
	}

	public AddressDetails toDetails(Address add) {
		return new AddressDetails(add.getAddressId(), add.getStreetNo(), add.getBuildingName(), add.getCity(),
				add.getState(), add.getCountry(), add.getPincode());
	}
}
