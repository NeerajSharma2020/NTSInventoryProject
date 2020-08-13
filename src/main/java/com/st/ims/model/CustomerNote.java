package com.st.ims.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "CustomerNote" database table.
 * 
 */
@Entity
@Table(name="\"CustomerNote\"")
public class CustomerNote implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"ID\"", unique=true, nullable=false)
	private Long id;

	@Column(name="\"CreateDate\"", nullable=false)
	private Timestamp createDate;

	@Column(name="\"CreatedByID\"")
	private Long createdByID;

	@Column(name="\"CustomerID\"", nullable=false)
	private Long customerID;

	@Column(name="\"Text\"", length=2147483647)
	private String text;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Long getCreatedByID() {
		return this.createdByID;
	}

	public void setCreatedByID(Long createdByID) {
		this.createdByID = createdByID;
	}

	public Long getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}