package com.st.ims.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the "Customer" database table.
 * 
 */
@Entity
@Table(name = "\"Customer\"")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"ID\"", unique = true, nullable = false)
	private Integer id;

	@Column(name = "\"AddressLine1\"", nullable = false, length = 2147483647)
	private String addressLine1;

	@Column(name = "\"AddressLine2\"", length = 2147483647)
	private String addressLine2;

	@Column(name = "\"City\"", nullable = false, length = 1)
	private String city;

	@Column(name = "\"CompanyName\"", nullable = false, length = 2147483647)
	private String[] companyName;

	@Column(name = "\"State\"", nullable = false, length = 1)
	private String state;

	@Column(name = "\"Zip\"", nullable = false, length = 1)
	private String zip;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String[] getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String[] companyName) {
		this.companyName = companyName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}