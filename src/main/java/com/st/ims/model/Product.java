package com.st.ims.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Product")
@Getter @Setter 
@NoArgsConstructor
@ToString
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "\"productID\"", nullable = false)
	private int productId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="\"factoryID\"")
	private Factory factory;
	

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="\"categoryID\"")
	private ProductCategory category;
	

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "\"subCategoryID\"")
	private ProductSubCategory subCategory;
	
	@Column(name = "\"partNumber\"" , nullable = false)
	private String partNumber;
	@Column(name = "\"description\"" , nullable = false)
	private String description;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="\"formatTypeID\"")
	private FormatType formatType;
	@Column(name = "\"indUPC\"" , nullable = false)
	private String indUPC;
	@Column(name = "\"caseUPC\"" , nullable = false)
	private String caseUPC;
	@Column(name = "\"EAN\"" , nullable = false)
	private String ean;
	@Column(name = "\"AlternateEAN\"" , nullable = false)
	private String alternateEAN;
	@Column(name = "\"UOM\"" , nullable = false)
	private String uom;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="\"StatusID\"")
	private Status status;
	
	@Column(name = "\"basePrice\"" , nullable = false)
	private BigDecimal basePrice;
	@Column(name = "cost" , nullable = false)
	private BigDecimal cost;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="\"priceByID\"")
	private PriceBy priceBy;
	
	@Column(name = "\"listPrice\"" , nullable = false)
	private int listPrice;
	@Column(name = "\"minOrderQty\"" , nullable = false)
	private int minOrderQty;
	@Column(name = "\"commisionRate\"" , nullable = false)
	private double commisionRate;
	@Column(name = "\"imageURL\"" , nullable = false)
	private String imageURL;
	public Product(Factory factory, ProductCategory category, ProductSubCategory subCategory, String partNumber,
			String description, FormatType formatType, String indUPC, String caseUPC, String ean, String alternateEAN,
			String uom, Status status, BigDecimal basePrice, BigDecimal cost, PriceBy priceBy, int listPrice,
			int minOrderQty, double commisionRate, String imageURL) {
		super();
		this.factory = factory;
		this.category = category;
		this.subCategory = subCategory;
		this.partNumber = partNumber;
		this.description = description;
		this.formatType = formatType;
		this.indUPC = indUPC;
		this.caseUPC = caseUPC;
		this.ean = ean;
		this.alternateEAN = alternateEAN;
		this.uom = uom;
		this.status = status;
		this.basePrice = basePrice;
		this.cost = cost;
		this.priceBy = priceBy;
		this.listPrice = listPrice;
		this.minOrderQty = minOrderQty;
		this.commisionRate = commisionRate;
		this.imageURL = imageURL;
	}
	
	
		

}