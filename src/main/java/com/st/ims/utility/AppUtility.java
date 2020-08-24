package com.st.ims.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.st.ims.model.Invoice;
import com.st.ims.model.InvoiceDetails;
import com.st.ims.model.Order;
import com.st.ims.model.OrderDetails;

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
	
	
	public static InvoiceDetails setDefaultValues(InvoiceDetails invoiceDetail) {
		try {
			Invoice invoice = invoiceDetail.getInvoice();
			invoice.setDueDate(getDueDate(invoice.getCreateDate()));
			invoice.setCommissionAmount(getCommissionAmount(invoice.getInvoiceAmount(),invoice.getCommissionPercentage()));
			invoice.setInvoiceNumber(getInvoiceNumber());
			invoiceDetail.setInvoice(invoice);
			return invoiceDetail;
		} catch (NoSuchAlgorithmException e) {
			logger.error("Exception while configuring dueDate,invoiceNumber and commissionAmount.",e);
		}
		return invoiceDetail;
		
		
	}
	
	public static OrderDetails setDefaultValues(OrderDetails orderDetail) {
		try {
			 Order order = orderDetail.getOrder();
			 order.setDueDate(getDueDate(order.getCreateDate()));
			 order.setCommissionAmount(getCommissionAmount(order.getInvoiceAmount(),order.getCommissionPercentage()));
			 order.setInvoiceNumber(getInvoiceNumber());
			 orderDetail.setOrder(order);
			return orderDetail;
		} catch (NoSuchAlgorithmException e) {
			logger.error("Exception while configuring dueDate,invoiceNumber and commissionAmount.",e);
		}
		return orderDetail;
		
		
	}
}
