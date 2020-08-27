package com.st.ims.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.st.ims.model.Invoice;
import com.st.ims.model.InvoiceDetails;
import com.st.ims.model.InvoiceProducts;

@Service
public class AppUtility {
	
	private static final Logger logger = Logger.getLogger(AppUtility.class);
	
	private AppUtility() {
		
	}
	/*
	 * This method will take LocalDateTime as date and will return after two
	 * months date.
	 */
	private static LocalDateTime getDueDate(LocalDateTime createdDate) {
		if(createdDate != null) {
			return createdDate.plusMonths(2);
		}
		return createdDate;
	
		
	}

	/* This method will return alphanumeric invoice number. */
	
	private static String getInvoiceNumber() throws NoSuchAlgorithmException {
		Random rand;
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
        rand = SecureRandom.getInstanceStrong();
 
		return rand.ints(leftLimit, rightLimit + 1)
		  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
		  .limit(targetStringLength)
		  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
		  .toString();
		   
	   
	}
	
	/*
	 * This method will take invoice amount and commission percentage and will return
	 * commission amount.
	 */
	private static double getCommissionAmount(double invoiceAmount , double commissionPercentage) {
		return invoiceAmount*(commissionPercentage/100);
	}
	
	/* This method will set default values for invoice object. */
	public static Invoice setDefaultValues(Invoice invoice) {
		try {
			invoice.setDueDate(getDueDate(invoice.getCreateDate()));
			invoice.setCommissionAmount(getCommissionAmount(invoice.getInvoiceAmount(),invoice.getCommissionPercentage()));
			invoice.setInvoiceNumber(getInvoiceNumber());
			return invoice;
		} catch (NoSuchAlgorithmException e) {
			logger.error("Exception while configuring dueDate,invoiceNumber and commissionAmount.",e);
		}
		return invoice;
	}

	/* This method will create a new InvoiceDetails List, so that we can persist invoice object with this list */
	public static List<InvoiceDetails> getInvoiceDetailsList(Invoice invoice, List<InvoiceProducts> invoiceProductList){
		List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();
		try {
			Invoice filledInvoice = setDefaultValues(invoice);
			if(!invoiceProductList.isEmpty()) {
				for(InvoiceProducts invoiceProduct:invoiceProductList){
					InvoiceDetails newInvoiceDetail = new InvoiceDetails();
					newInvoiceDetail.setInvoice(filledInvoice);
					newInvoiceDetail.setProduct(invoiceProduct.getProduct());
					newInvoiceDetail.setProductCount(invoiceProduct.getProductQuantity());
					invoiceDetailsList.add(newInvoiceDetail);
				}
			}else {
				logger.error("Invoice doesn,t have any product associated with it.");
			}
			    return invoiceDetailsList;
		} catch (Exception e) {
			logger.error("Exception while getting InvoiceDetailsList.",e);
		}
		return invoiceDetailsList;
	}
	
	/* This method will create new InvoiceDetails list for update operation. */
	public static List<InvoiceDetails> getUpdatedInvoiceList(Invoice invoice,List<InvoiceProducts> invoiceProductList){
	
		List<InvoiceDetails> invoiceDetailsList = new ArrayList<>();
		try {
			for(InvoiceProducts invoiceProduct:invoiceProductList) {
				 InvoiceDetails invoiceDetail = new InvoiceDetails();
				 invoiceDetail.setInvoice(invoice);
				 invoiceDetail.setProduct(invoiceProduct.getProduct());
				 invoiceDetail.setProductCount(invoiceProduct.getProductQuantity());
				 invoiceDetailsList.add(invoiceDetail);
				 
			 }
			 return invoiceDetailsList;
		}catch(Exception e) {
			logger.error("Exception while setting invoice detials list for updation.",e);
		}
		return invoiceDetailsList;
	}
	
}
