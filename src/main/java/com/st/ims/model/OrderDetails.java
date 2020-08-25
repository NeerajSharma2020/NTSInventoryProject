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
import lombok.ToString;

@Entity
@Table(name = "\"orderDetails\"")
@Setter @Getter
@NoArgsConstructor
@ToString
public class OrderDetails implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "\"orderDetailID\"", nullable = false)
	private int orderDetailId;
	
	// CascadeType.PERSIST will restrict to delete the invoice record while deleting OrderDetails.
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"orderID\"")
	private Order order;
	
	// CascadeType.PERSIST will restrict to delete the product record while deleting OrderDetails.
	  @OneToOne(cascade = CascadeType.MERGE)
	  @JoinColumn(name="\"productID\"")
	 private Product product;
	 
	 @Column(name = "\"productCount\"")
	 private int productCount = 1; 
}
