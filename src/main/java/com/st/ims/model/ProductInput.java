package com.st.ims.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Factory factoryID;
	private ProductCategory categoryID;
	private ProductSubCategory subCategoryID;
	private String partNumber;
	private String description;
	private FormatType formatTypeID;
	private String indUPC;
	private String caseUPC;
	private String ean;
	private String alternateEAN;
	private String uom;
	private Status statusID;
	private BigDecimal basePrice;
	private BigDecimal cost;
	private PriceBy priceById;
	private int listPrice;
    private int minOrderQty;
    private double commisionRate;
	private String imageURL;


	
}
