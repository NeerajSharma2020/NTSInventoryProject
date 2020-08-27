package com.st.ims.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class InvoiceProducts {
	
	private Product product;
	private int productQuantity;

}
