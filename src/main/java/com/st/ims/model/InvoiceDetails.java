package com.st.ims.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"invoiceDetails\"")
@Setter @Getter
@NoArgsConstructor
public class InvoiceDetails implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "\"invoiceDetailID\"", nullable = false)
	private int invoiceDetailId;
	
	// CascadeType.PERSIST will restrict to delete the invoice record while deleting InvoiceDetails.
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"invoiceID\"")
	private Invoice invoice;
	
	// CascadeType.PERSIST will restrict to delete the product record while deleting InvoiceDetails.
	  @OneToOne(cascade = CascadeType.MERGE)
	  @JoinColumn(name="\"productID\"")
	 private Product product;
	 
	 @Column(name = "\"productCount\"",columnDefinition = "integer default 1")
	 private int productCount = 1; 

	
	
	

}
