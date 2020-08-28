package com.st.ims.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "\"order\"")
@Setter @Getter
@NoArgsConstructor
@ToString
public class Order implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "\"orderID\"", nullable = false)
	private int orderId;
	@Column(name = "\"createDate\"" , nullable = false)
	private LocalDateTime createDate;
	@Column(name = "\"dueDate\"" , nullable = false)
	private LocalDateTime dueDate;
	@Column(name = "\"orderNumber\"" , nullable = false)
	private String orderNumber;
	@Column(name = "\"status\"" , nullable = false)
	private String status;
	@Column(name = "\"factSONumber\"" , nullable = false)
	private String factSONumber;
	@Column(name = "\"proNumber\"" , nullable = false)
	private String proNumber;
	@Column(name = "\"notes\"" , nullable = false)
	private String notes;
	@Column(name = "\"orderAmount\"" , nullable = false)
	private double orderAmount;
	@Column(name = "\"commissionPercentage\"" , nullable = false)
	private double commissionPercentage;
	@Column(name = "\"commissionAmount\"" , nullable = false)
	private double commissionAmount;
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrderDetails> orderDetails;

}