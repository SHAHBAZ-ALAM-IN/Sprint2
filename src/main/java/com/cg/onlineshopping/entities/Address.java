package com.cg.onlineshopping.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
/**
 * The Address program contains all the entities and constructor method that
 * call all the variable in Address
 * 
 * @author tushar arora
 * @version 1.0
 * @since 2020-10-19
 */

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * This constructor is used to initialize objects of address class.
	 * 
	 * @Id Specifies the primary key of an entity
	 * @Entity defines that a class can be mapped to a table
	 * @GeneratedValue Provides for the specification of generation strategies for
	 *                 the values of primary keys.
	 * @Column permits the name of the column to be explicitly specified
	 * @param addressId    this is the addressId for Address method
	 * @param streetNo     this is the streetNo for Address method
	 * @param buildingName this is the buildingName for Address method
	 * @param city         this is the city for Address method
	 * @param state        this is the state for Address method
	 * @param country      this is the country for Address method
	 * @param pincode      this is the pincode for Address method
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	private String streetNo;
	private String buildingName;
	private String city;
	private String state;
	private String country;
	private String pincode;

	public Address() {
	}

	public Address(String streetNo, String buildingName, String city, String state, String country, String pincode) {

		this.streetNo = streetNo;
		this.buildingName = buildingName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

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

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", streetNo=" + streetNo + ", buildingName=" + buildingName
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", pincode=" + pincode + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return addressId == other.addressId;
	}

}
