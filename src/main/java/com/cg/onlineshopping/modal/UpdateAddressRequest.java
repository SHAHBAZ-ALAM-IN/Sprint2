package com.cg.onlineshopping.modal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateAddressRequest {
	private int addressId;
	@NotBlank
	@Size(min = 2, max = 20)
	private String streetNo;
	@NotBlank
	@Size(min = 2, max = 20)
	private String buildingName;
	@NotBlank
	@Size(min = 2, max = 20)
	private String city;
	@NotBlank
	@Size(min = 2, max = 20)
	private String state;
	@NotBlank
	@Size(min = 2, max = 20)
	private String country;
	@NotBlank
	@Size(min = 2, max = 20)
	private String pincode;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
